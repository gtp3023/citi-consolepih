package com.citi.cpih.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.cpih.dto.UserDTO;
import com.citi.cpih.service.ConsoleService;
import com.citi.cpih.service.GwtService;
import com.citi.cpih.service.SprService;

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
	
	@Override
    public UserDTO search(String msisdn) {
		UserDTO userDTO = new UserDTO(msisdn);
		userDTO.setFullName("Jorge Ruiz");
		userDTO.setEmail("jorge.ruiz@citi.com.mx");
		userDTO.setMsisdnTwo("8114111585");
		userDTO.setHasMh3("Sí");
		userDTO.setHasGeolk("Sí");
		userDTO.setHasOfferId("No");
		userDTO.setSubscription("Pospago");
		userDTO.setHasVpn("Correcto");
		userDTO.setLastChangeDate("12/20/2021");
		userDTO.setHasRegister("Sí");
		userDTO.setCreationDate("10/20/2021");
        return userDTO;
    }
	
	
}