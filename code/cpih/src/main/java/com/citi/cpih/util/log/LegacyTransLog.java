package com.citi.cpih.util.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.citi.cpih.dto.ResponseDTO;
import com.citi.cpih.dto.UserDTO;
import com.citi.cpih.util.Constants;
import com.citi.cpih.util.Util;

/**
 * @author jorge.ruiz citi.com.mx
 */
@Service("legacyTransLog")
public class LegacyTransLog {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private DateFormat dateFormatterFull = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public void logGetInternetBalanceV4(UserDTO userDTO, Date creationDate, ResponseDTO response) {
		String date = this.formatDateFull(creationDate);
		this.logger.info("{} - {}|{}|{}|{}|{}",
				Constants.GWT_METHOD_GET_INTERNET_BALANCE_V4,
				date,
				userDTO.getMsisdn(),
				Util.getSubscriberType(userDTO.getSubscription()),
				response.getCode(),
				response.getDescription());
	}
	
	private String formatDateFull(Date creationDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.dateFormatterFull.format(creationDate)).append("|").append(this.dateFormatterFull.format(new Date()));
		return sb.toString();
	}
	
}