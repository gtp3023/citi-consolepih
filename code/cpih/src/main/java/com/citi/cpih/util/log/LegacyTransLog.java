package com.citi.cpih.util.log;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.citi.cpih.dto.ResponseDTO;
import com.citi.cpih.dto.UserDTO;
import com.citi.cpih.util.Constants;
import com.citi.cpih.util.DateUtil;

/**
 * @author jorge.ruiz citi.com.mx
 */
@Service("legacyTransLog")
public class LegacyTransLog {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	public void logGetInternetBalanceV4(UserDTO userDTO, ResponseDTO response) {
		String creationDate = DateUtil.formatDateLog(new Date());
		this.logger.info("{} - {}|{}|{}|{}",
				Constants.GWT_METHOD_GET_INTERNET_BALANCE_V4,
				userDTO.getMsisdn(),
				creationDate,
				response.getCode(),
				response.getDescription());
	}
	
	public void logGetCloudInfo(UserDTO userDTO, ResponseDTO response) {
		String creationDate = DateUtil.formatDateLog(new Date());
		this.logger.info("{} - {}|{}|{}|{}",
				Constants.GWT_METHOD_GET_CLOUD_INFO,
				userDTO.getMsisdn(),
				creationDate,
				response.getCode(),
				response.getDescription());
	}
	
	public void getSubscriptionInfo(String msisdn, ResponseDTO responseDTO, String productId) {
		String creationDate = DateUtil.formatDateLog(new Date());
		this.logger.info("{} - {}|{}|{}|{}|{}",
				Constants.TANGO_GET_SUBSCRIPTION_INFO,
				msisdn,
				creationDate,
				productId,
				responseDTO.getCode(),
				responseDTO.getDescription());
	}
	
}