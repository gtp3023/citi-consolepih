package com.citi.cpih.service;

import com.citi.cpih.dto.ResponseDTO;

/**
 * @author jruizg citi.com.mx
 */
public interface TangoService {
	
	ResponseDTO getSubscriptionInfo(String msisdn);
	
}