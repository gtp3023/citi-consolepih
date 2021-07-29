package com.citi.cpih.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.citi.cpih.dto.ResponseDTO;
import com.citi.cpih.dto.SubscriptionInfoDTO;
import com.citi.cpih.service.TangoService;
import com.citi.cpih.util.Constants;
import com.citi.cpih.util.log.LegacyTransLog;
import com.citi.cpih.ws.HttpClientPoolManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jruizg citi.com.mx
 */
@Service("tangoService")
public class TangoServiceImpl implements TangoService {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	public static final String PARAM_MSISDN = "pMSISDN";
	
	@Value("${pih.offer.id}")
	private String offerId;
	
	@Value("${tango.url}")
	private String tangoUrl;
	
	@Value("${tango.timeout}")
	private int tangoTimeout;
	
	@Value("${tango.service.getSubscriptionInfo}")
	private String getSubscriptionInfo;
	
	@Autowired
	private LegacyTransLog legacyTransLog;
	
	@Autowired
	private HttpClientPoolManager httpClientPoolManager;
	
	@Override
    public ResponseDTO getSubscriptionInfo(String msisdn) {
		ResponseDTO response = new ResponseDTO();
		
		try {
			msisdn = msisdn.length() == 12 ? msisdn.substring(2) : msisdn;
			
			this.logger.info("{} - Invoking Tango getSubscriptionInfo", msisdn);
			
			String url = this.tangoUrl + this.getSubscriptionInfo.replace(PARAM_MSISDN, msisdn);
			CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(httpClientPoolManager.getPoolingManager()).build();
			
	        RequestConfig defaultRequestConfig = RequestConfig.custom()
	            .setCookieSpec(CookieSpecs.STANDARD)
	            .setSocketTimeout(tangoTimeout)
	            .build();
			
	        HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(defaultRequestConfig);
	        
			ResponseHandler<String> responseHandler = null;
				
			responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response) throws IOException {
                    int status = response.getStatusLine().getStatusCode();
                    logger.info("Http Client Response code {} ", status);
                    
                    if (status == 200) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        logger.info("Http Error Message :: {}", status);
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            String responseXml = httpClient.execute(httpGet, responseHandler);
			
			this.logger.info("{} - getSubscriptionInfo url :: {}", msisdn, url);
			this.logger.info("{} - getSubscriptionInfo response :: {}", msisdn, responseXml);
			
			boolean hasOfferId = this.validateResponseSubscriptionInfo(response, responseXml, msisdn);
			
			if(hasOfferId) {
				response.setCode(Constants.CODE_SUCCESS);
				response.setDescription(Constants.MSG_SUCCESS);
				response.setHasOfferId(Constants.SI);
			} else {
				response.setCode(Constants.CODE_ERROR);
				response.setDescription(Constants.MSG_ERROR);
				response.setHasOfferId(Constants.NO);
			}
		} catch (Exception e) {
			this.logger.error("{} - Error Tango getSubscriptionInfo => {}", msisdn, e.getMessage());
			
			response.setCode(Constants.CODE_ERROR);
			response.setDescription(Constants.MSG_ERROR);
			response.setHasOfferId(Constants.NO);
		}
		
		this.legacyTransLog.getSubscriptionInfo(msisdn, response, this.offerId);
		
		return response;
    }
	
	private boolean validateResponseSubscriptionInfo(ResponseDTO response, String responseXml, String msisdn) {
		boolean hasOfferId = Boolean.FALSE;
		Document docXml = this.convertStringToDocument(msisdn, responseXml);
		
		if(docXml != null) {
			hasOfferId = this.getResponseFromXml(msisdn, docXml);
		}
		
		return hasOfferId;
	}
	
	private boolean getResponseFromXml(String msisdn, Document docXml) {
		boolean hasOfferId = Boolean.FALSE;
		
		try {
			this.logger.info("{} - Validating offers list", msisdn);
			NodeList nList = docXml.getElementsByTagName("offersList");
			
			if(nList != null && nList.getLength() > 0) {
			    Node currentItem = nList.item(0);
			    String content = currentItem.getTextContent();
			    
			    if(content != null && !content.isEmpty()) {
			    	hasOfferId = getSubscriptionInfoDTO(msisdn, hasOfferId, content);
			    }
			}
		} catch (Exception e) {
			this.logger.info("{} - Line without offerId", msisdn);
			hasOfferId = Boolean.FALSE;
		}
			
		return hasOfferId;
	}
	
	private boolean getSubscriptionInfoDTO(String msisdn, boolean hasOfferId, String content) {
		if(!hasOfferId) {
			List<SubscriptionInfoDTO> list = this.getListFromMapper(content);
		    
		    for (SubscriptionInfoDTO info : list) {
		    	this.logger.info("{} - Validating node with text :: {}", msisdn, info);
		    	
		    	if(!info.getOfferID().isEmpty() && info.getOfferID().trim().equals(this.offerId)) {
	    			this.logger.info("{} - line with product offer {}", msisdn, this.offerId);
	    			hasOfferId = Boolean.TRUE;
		    		break;
		    	}
			}
		}
	    
	    return hasOfferId;
	}
	
	private List<SubscriptionInfoDTO> getListFromMapper(String content) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(content, new TypeReference<List<SubscriptionInfoDTO>>() {});
		} catch (Exception e) {
			this.logger.info("{} - Error reading content", content);
			return new ArrayList<>();
		}
	}
	
	private Document convertStringToDocument(String msisdn, String xmlStr) {
		logger.info("{} - Convert string to document xml", msisdn);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        
        DocumentBuilder builder;
        
        try {
            builder = factory.newDocumentBuilder();  
            return builder.parse( new InputSource( new StringReader( xmlStr ) ) );
        } catch (Exception e) {
        	logger.error("{} - Error on convert string to document xml :: {}", msisdn, e.getMessage());
        }
        
        return null;
    }
	
}