package com.citi.cpih.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.cpih.dto.ResponseDTO;
import com.citi.cpih.dto.UserDTO;
import com.citi.cpih.dto.UserSprDTO;
import com.citi.cpih.model.UserPih;
import com.citi.cpih.service.ConsoleService;
import com.citi.cpih.service.GwtService;
import com.citi.cpih.service.SprService;
import com.citi.cpih.service.TangoService;
import com.citi.cpih.service.UserService;
import com.citi.cpih.util.Constants;
import com.citi.cpih.util.DateUtil;

/**
 * @author jruizg citi.com.mx
 */
@Service("consoleService")
public class ConsoleServiceImpl implements ConsoleService {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired 
    private GwtService gwtService;
	
	@Autowired 
    private SprService sprService;
	
	@Autowired 
    private UserService userService;
	
	@Autowired 
    private TangoService tangoService;
	
	@Override
    public UserDTO getUserSpr(String msisdn) {
		msisdn = Constants.LADA + msisdn;
		
		logger.info("{} Load user from SPR", msisdn);
		UserSprDTO userSprDTO = this.sprService.getProfile(msisdn);
		UserDTO userDTO = null;
		
		if(userSprDTO != null) {
			userDTO = new UserDTO(msisdn);
			userDTO.setSubscription(userSprDTO.getSubscription());
			userDTO.setHasVpn(userSprDTO.getVpnNodeId() == 5 ? Constants.VPN_CORRECT : Constants.VPN_INCORRECT);
		}
		
		return userDTO;
	}
	
	@Override
    public UserDTO search(String msisdn) {
		logger.info("{} Search information", msisdn);
		UserDTO userDTO = this.getUser(msisdn);
		
		logger.info("{} Validate getInternetBalance", msisdn);
		ResponseDTO responseV4 = this.gwtService.getInternetBalanceV4(userDTO);
		
		logger.info("{} Validate getCloudInfo", msisdn);
		ResponseDTO responseCloud = this.gwtService.getCloudInfo(userDTO);
		
		userDTO.setHasMh3(responseV4.getHasMh3());
		userDTO.setHasGeolk(responseCloud.getHasGeolk());
		userDTO.setLastChangeDate(responseV4.getLastChangeGeolk());
		
		if(userDTO.getHasMh3().equals(Constants.SI)) {
			logger.info("{} Validate getSubscriptionInfo", msisdn);
			ResponseDTO responseSubscriptionInfo = this.tangoService.getSubscriptionInfo(msisdn);
			userDTO.setHasOfferId(responseSubscriptionInfo.getHasOfferId());
		} else {
			userDTO.setHasOfferId(Constants.NO);
		}
		
        return userDTO;
    }
	
	private UserDTO getUser(String msisdn) {
		msisdn = Constants.LADA + msisdn;
		
		logger.info("{} Load user from database", msisdn);
		UserPih userPih = this.userService.getUserPih(msisdn);
		UserDTO userDTO = new UserDTO(msisdn);
		
		
		if(userPih != null) {
			userDTO.setFullName(userPih.getFullName());
			userDTO.setEmail(userPih.getEmail());
			userDTO.setMsisdnTwo(userPih.getMsisdnTwo());
			
			userDTO.setHasRegister(Constants.SI);
			userDTO.setCreationDate(DateUtil.formatDate(userPih.getCreated()));
		} else {
			userDTO.setHasRegister(Constants.NO);
			userDTO.setLastChangeDate(Constants.NA);
			userDTO.setCreationDate(Constants.NA);
		}
		
		return userDTO;
	}
	
}