package com.citi.cpih.service;

import com.citi.cpih.dto.UserDTO;

/**
 * @author jruizg citi.com.mx
 */
public interface ConsoleService {
	
	UserDTO getUserSpr(String msisdn);
	
	UserDTO search(String msisdn, UserDTO userSession);
    
}