package com.citi.cpih.service.impl;

import java.util.Date;
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
        Date creationDate = new Date();

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

            if (operationResponseType != null) {
                response.setCode(operationResponseType.getResponseCode());
                response.setDescription(operationResponseType.getResponseMessage());
            	
                if (operationResponseType.getResponseCode() == 0) {
                    Iterator<ValuesType> i = operationResponseType.getValues().iterator();

                    while(i.hasNext()) {
                        ValuesType v = i.next();
                        
                        if (null != v && null != v.getName()) {
	                        if (v.getName().contains("Natural") && null != v.getMapValues() ) {
								List<MapValueType> map = v.getMapValues();
								
								for (MapValueType mapValueType : map) {
									if(mapValueType.getAttribute().equals("Product")) {
										//userDTO.setPlan(mapValueType.getValue());
										break;
									}
								}
							}
	                        
                        }
                    }
                    
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
        
        this.legacyTransLog.logGetInternetBalanceV4(userDTO, creationDate, response);
        return response;
    }
	
	@Override
    public ResponseDTO getCloudInfo(UserDTO userDTO) {
		ResponseDTO response = new ResponseDTO();
        Date creationDate = new Date();
        
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
			
			if (responseMap != null) {
				response = new ResponseDTO();
				
				Iterator<SoapMapValue> i = responseMap.getValues().iterator();

	            while(i.hasNext()) {
	            	SoapMapValue v = i.next();
	            	
	            	if(v.getName().equalsIgnoreCase("responseCode")) {
	            		response.setCode(Integer.valueOf(v.getSingleValue()));
					}
	            }
				
				this.logger.info("{} - Response GWT buyCloudProduct [ Codigo: {}, Mensaje: {} ]", userDTO.getMsisdn(), 
						response.getCode(), response.getDescription());
			} else {
				response = new ResponseDTO();
				response.setCode(Constants.CODE_WS_EXCEPTION);
				response.setDescription("Error GWTSVA invoke getCloudInfo");
				
				this.logger.info("{} - Response GWT buyCloudProduct => [ Codigo: {}, Mensaje: {} ]", userDTO.getMsisdn(), 
						response.getCode(), response.getDescription());
			}
            
        } catch(Exception e) {
        	this.logger.error("{} - An error occurred when consulting getCloudInfo - {}", userDTO.getMsisdn(), e.getMessage());
			
			response = new ResponseDTO();
			response.setCode(Constants.CODE_WS_EXCEPTION);
			response.setDescription("Error GWTSVA invoke getCloudInfo");
        }
        
        this.legacyTransLog.logGetCloudInfo(userDTO, creationDate, response);
        return response;
    }
	
	
}