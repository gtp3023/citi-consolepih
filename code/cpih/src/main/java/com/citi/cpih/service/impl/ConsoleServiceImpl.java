package com.citi.cpih.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.cpih.dto.UserDTO;
import com.citi.cpih.dto.UserSprDTO;
import com.citi.cpih.model.UserPih;
import com.citi.cpih.service.ConsoleService;
import com.citi.cpih.service.GwtService;
import com.citi.cpih.service.SprService;
import com.citi.cpih.service.UserService;
import com.citi.cpih.util.Constants;

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
	
	@Override
    public UserDTO search(String msisdn) {
		UserDTO userDTO = this.getUser(msisdn);
		
		this.gwtService.getInternetBalanceV4(userDTO);
		this.gwtService.getCloudInfo(userDTO);
		
		userDTO.setHasMh3(Constants.SI);
		userDTO.setHasGeolk(Constants.SI);
		userDTO.setHasOfferId(Constants.SI);
		
		userDTO.setLastChangeDate("12/20/2021");
		
        return userDTO;
    }
	
	private UserDTO getUser(String msisdn) {
		msisdn = Constants.LADA + msisdn;
		UserPih userPih = this.userService.getUserPih(msisdn);
		UserDTO userDTO = new UserDTO(msisdn);
		UserSprDTO userSprDTO = null;
		
		if(userPih != null) {
			userSprDTO = this.sprService.getProfile(msisdn);
			
			userDTO.setFullName(userPih.getFullName());
			userDTO.setEmail(userPih.getEmail());
			userDTO.setMsisdnTwo(userPih.getMsisdnTwo());
			
			userDTO.setHasRegister(Constants.SI);
			userDTO.setCreationDate("10/20/2021");
		} else {
			userDTO.setHasRegister(Constants.NO);
			userDTO.setLastChangeDate(Constants.NA);
			userDTO.setCreationDate(Constants.NA);
		}
		
		if(userSprDTO != null) {
			userDTO.setSubscription(userSprDTO.getSubscription());
			userDTO.setHasVpn(userSprDTO.getVpnNodeId() == 5 || userSprDTO.getVpnNodeId() == 7 ? "Correcto" : "Incorrecto");
		}
		
		return userDTO;
	}
	
}