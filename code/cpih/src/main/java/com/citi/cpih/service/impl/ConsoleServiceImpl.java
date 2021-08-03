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
			userDTO.setHasVpn(userSprDTO.getVpnNodeId() != null && userSprDTO.getVpnNodeId() == 5 ? Constants.VPN_CORRECT : Constants.VPN_INCORRECT);
		}
		
		return userDTO;
	}
	
	@Override
    public UserDTO search(String msisdn, UserDTO userSession) {
		logger.info("{} Search information", msisdn);
		UserDTO userDTO = this.getUser(msisdn, userSession);
		
		logger.info("{} Validate getInternetBalance", msisdn);
		ResponseDTO responseV4 = this.gwtService.getInternetBalanceV4(userDTO);
		
		logger.info("{} Validate getCloudInfo", msisdn);
		ResponseDTO responseCloud = this.gwtService.getCloudInfo(userDTO);
		
		userDTO.setHasGeolk(responseCloud.getHasGeolk());
		userDTO.setLastChangeDate(responseV4.getLastChangeGeolk());
		
		if(userDTO.getSubscription().equals(Constants.POSPAGO_BES)) {
			userDTO.setHasOfferId(Constants.NO_APLICA);
			userDTO.setHasMh3(Constants.NO_APLICA);
		} else {
			userDTO.setHasMh3(responseV4.getHasMh3());
			
			logger.info("{} Validate getSubscriptionInfo", msisdn);
			ResponseDTO responseSubscriptionInfo = this.tangoService.getSubscriptionInfo(msisdn);
			userDTO.setHasOfferId(responseSubscriptionInfo.getHasOfferId());
		}
		
        return userDTO;
    }
	
	private UserDTO getUser(String msisdn, UserDTO userSession) {
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
		
		if(userSession != null) {
			userDTO.setSubscriptionType(userSession.getSubscriptionType());
			userDTO.setSubscription(userSession.getSubscription());
			userDTO.setHasVpn(userSession.getHasVpn());
		}
		
		return userDTO;
	}
	
}