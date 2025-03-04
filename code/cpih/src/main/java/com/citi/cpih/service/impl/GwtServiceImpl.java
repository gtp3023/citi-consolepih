package com.citi.cpih.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.cpih.dto.ResponseDTO;
import com.citi.cpih.dto.UserDTO;
import com.citi.cpih.service.GwtService;
import com.citi.cpih.util.Constants;
import com.citi.cpih.util.DateUtil;
import com.citi.cpih.util.log.LegacyTransLog;

import mx.com.citi.captivecore.CaptiveCore;
import mx.com.citi.captivecore.MapValueType;
import mx.com.citi.captivecore.OperationRequestType;
import mx.com.citi.captivecore.OperationResponseType;
import mx.com.citi.captivecore.SoapMapType;
import mx.com.citi.captivecore.SoapMapValue;
import mx.com.citi.captivecore.ValuesType;

/**
 * @author jruizg citi.com.mx
 */
@Service("gwtService")
public class GwtServiceImpl implements GwtService {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired 
    private CaptiveCore captiveCore;
	
	@Autowired
	private LegacyTransLog legacyTransLog;
	
	@Override
    public ResponseDTO getInternetBalanceV4(UserDTO userDTO) {
		ResponseDTO response = new ResponseDTO();

        String serviceName = "getInternetBalanceV4";
        String serviceVersion = "1.0";
        String channel = "1";
        
        try {
            ValuesType valuesType = new ValuesType();
            valuesType.setName("platform");
            valuesType.setValue("Android");

            ValuesType valuesType1 = new ValuesType();
            valuesType1.setName("appVersion");
            valuesType1.setValue("3.2");

            OperationRequestType request = new OperationRequestType();
            request.getValues().add(valuesType);
            request.getValues().add(valuesType1);
            request.setMsisdn(userDTO.getMsisdn());
            request.setChannel(channel);
            request.setServiceName(serviceName);
            request.setServiceVersion(serviceVersion);
            
            this.logger.info("{} - Invoking GWT executeOperation serviceName {}", userDTO.getMsisdn(), serviceName);
            CaptiveCore port = this.captiveCore;
            OperationResponseType operationResponseType = port.executeOperation(request);
            
            response.setHasMh3(Constants.NO);
            response.setLastChangeGeolk(Constants.NA);

            if (operationResponseType != null) {
                response.setCode(operationResponseType.getResponseCode());
                response.setDescription(operationResponseType.getResponseMessage());
            	
                if (operationResponseType.getResponseCode() == 0) {
                    Iterator<ValuesType> i = operationResponseType.getValues().iterator();
                    List<String> listChangeLocationDate = new ArrayList<>();

                    while(i.hasNext()) {
                        ValuesType v = i.next();
                        
                        this.validateOtros(v, response, listChangeLocationDate);
                        this.validateHistorico(v, listChangeLocationDate);
                    }
                    
                    this.validateLastChangeLocationDate(response, listChangeLocationDate);
                }
            } else {
            	response.setCode(Constants.CODE_WS_EXCEPTION);
            	response.setDescription("Error GWTSVA executeOperation " + serviceName);
            }
            
        } catch(Exception e) {
        	this.logger.error("{} - An error occurred when consulting {} GWTSVA - {}", userDTO.getMsisdn(), serviceName, e.getMessage());
			
			response = new ResponseDTO();
			response.setCode(Constants.CODE_WS_EXCEPTION);
			response.setDescription("Error GWTSVA executeOperation " + serviceName);
        }
        
        this.legacyTransLog.logGetInternetBalanceV4(userDTO, response);
        return response;
    }
	
	private void validateOtros(ValuesType v, ResponseDTO response, List<String> listChangeLocationDate) {
		if (v.getName().contains("Otros") && null != v.getMapValues() ) {
			List<MapValueType> map = v.getMapValues();
			boolean isChangeLocationProduct = Boolean.FALSE;
			
			for (MapValueType mapValueType : map) {
				this.validateMh3(mapValueType, response);
				isChangeLocationProduct = this.validateChangeLocationProduct(mapValueType, isChangeLocationProduct);
				isChangeLocationProduct = this.validateChangeLocationDate(mapValueType, isChangeLocationProduct, listChangeLocationDate);
			}
		}
	}
	
	private void validateHistorico(ValuesType v, List<String> listChangeLocationDate) {
		if (v.getName().contains("Historico") && null != v.getMapValues() ) {
			List<MapValueType> map = v.getMapValues();
			boolean isChangeLocationProduct = Boolean.FALSE;
			
			for (MapValueType mapValueType : map) {
				isChangeLocationProduct = this.validateChangeLocationProduct(mapValueType, isChangeLocationProduct);
				isChangeLocationProduct = this.validateChangeLocationDate(mapValueType, isChangeLocationProduct, listChangeLocationDate);
			}
		}
	}
	
	private void validateMh3(MapValueType mapValueType, ResponseDTO response) {
		if(mapValueType.getAttribute().equals("productId") && mapValueType.getValue().equalsIgnoreCase("MH3")) {
			response.setHasMh3(Constants.SI);
		}
	}
	
	private boolean validateChangeLocationProduct(MapValueType mapValueType, boolean isChangeLocationProduct) {
		boolean isProductChange = isChangeLocationProduct;
		
		if(mapValueType.getAttribute().equals("productId") && (mapValueType.getValue().equalsIgnoreCase("MHF") || mapValueType.getValue().equalsIgnoreCase("MXO00")) ) {
			isProductChange = Boolean.TRUE;
		}
		
		return isProductChange;
	}
	
	private boolean validateChangeLocationDate(MapValueType mapValueType, boolean isChangeLocationProduct, List<String> listChangeLocationDate) {
		boolean retVal = isChangeLocationProduct;
		
		if(isChangeLocationProduct && mapValueType.getAttribute().equals("ProductActivationDate") && mapValueType.getValue() != null && !mapValueType.getValue().trim().isEmpty()) {
			String activationDate = mapValueType.getValue().trim().substring(0, 10);
			activationDate = activationDate.replace("-", "/");
			listChangeLocationDate.add(activationDate);
			retVal = Boolean.FALSE;
		}
		
		return retVal;
	}
	
	private void validateLastChangeLocationDate(ResponseDTO response, List<String> listChangeLocationDate) {
		if(!listChangeLocationDate.isEmpty()) {
			String date = null;
			
			for (String changeLocationDate : listChangeLocationDate) {
				if(date == null) {
					date = changeLocationDate;
				}
				
				if(DateUtil.formatDate(changeLocationDate).after(DateUtil.formatDate(date))) {
					date = changeLocationDate;
				}
			}
			
			response.setLastChangeGeolk(date);
		}
	}
	
	@Override
    public ResponseDTO getCloudInfo(UserDTO userDTO) {
		ResponseDTO response = new ResponseDTO();
        
        try {
        	SoapMapType request = new SoapMapType();
			
            SoapMapValue soapMapValue = new SoapMapValue();
            soapMapValue.setName("serviceName");
            soapMapValue.setSingleValue("getCloudInfo");
			
            request.getValues().add(soapMapValue);
            
            soapMapValue = new SoapMapValue();
            soapMapValue.setName("msisdn");
            soapMapValue.setSingleValue(userDTO.getMsisdn());
			
            request.getValues().add(soapMapValue);
            
            soapMapValue = new SoapMapValue();
            soapMapValue.setName("channel");
            soapMapValue.setSingleValue("1");
			
            request.getValues().add(soapMapValue);
            
            soapMapValue = new SoapMapValue();
            soapMapValue.setName("serviceVersion");
            soapMapValue.setSingleValue("2.0");
			
            request.getValues().add(soapMapValue);
            
            soapMapValue = new SoapMapValue();
            soapMapValue.setName("amount");
            soapMapValue.setSingleValue("0");
			
            request.getValues().add(soapMapValue);
            
			this.logger.info("{} - Invoking GWT getCloudInfo", userDTO.getMsisdn());
			CaptiveCore port = this.captiveCore;
			SoapMapType responseMap = port.invoke(request);
			
            response.setHasGeolk(Constants.NO);
			
			if (responseMap != null) {
				Iterator<SoapMapValue> i = responseMap.getValues().iterator();

	            while(i.hasNext()) {
	            	SoapMapValue v = i.next();
	            	
	            	this.validateProductsCloud(v, response);
	            }
				
				this.logger.info("{} - Response GWT getCloudInfo [ Codigo: {}, Mensaje: {} ]", userDTO.getMsisdn(), 
						response.getCode(), response.getDescription());
			} else {
				response = new ResponseDTO();
				response.setCode(Constants.CODE_WS_EXCEPTION);
				response.setDescription("Error GWTSVA invoke getCloudInfo");
				
				this.logger.info("{} - Response GWT getCloudInfo => [ Codigo: {}, Mensaje: {} ]", userDTO.getMsisdn(), 
						response.getCode(), response.getDescription());
			}
            
        } catch(Exception e) {
        	this.logger.error("{} - An error occurred when consulting getCloudInfo - {}", userDTO.getMsisdn(), e.getMessage());
			
			response = new ResponseDTO();
			response.setCode(Constants.CODE_WS_EXCEPTION);
			response.setDescription("Error GWTSVA invoke getCloudInfo");
        }
        
        this.legacyTransLog.logGetCloudInfo(userDTO, response);
        return response;
    }
	
	private void validateProductsCloud(SoapMapValue v, ResponseDTO response) {
		if(null != v.getMapArrayValue() && !v.getMapArrayValue().isEmpty()) {
			List<SoapMapType> map = v.getMapArrayValue();
			
			for (SoapMapType mapValueType : map) {
				this.validateProductId(mapValueType, response);
			}
		}
	}
	
	private void validateProductId(SoapMapType mapValueType, ResponseDTO response) {
		if(null != mapValueType.getValues() && !mapValueType.getValues().isEmpty()) {
			for (SoapMapValue soapMapValue : mapValueType.getValues()) {
				if(null != soapMapValue.getMapArrayValue() && !soapMapValue.getMapArrayValue().isEmpty()) {
					for (SoapMapType soapMapType : soapMapValue.getMapArrayValue()) {
						this.validateProductId2(soapMapType, response);
					}
				}
			}
		}
	}
	
	private void validateProductId2(SoapMapType soapMapType, ResponseDTO response) {
		if(null != soapMapType.getValues() && !soapMapType.getValues().isEmpty()) {
			for (SoapMapValue soapMapValue : soapMapType.getValues()) {
				if(soapMapValue.getName().equals("id") && soapMapValue.getSingleValue().equals("GEOLK")) {
					response.setHasGeolk(Constants.SI);
					break;
				}
			}
		}
	}

}