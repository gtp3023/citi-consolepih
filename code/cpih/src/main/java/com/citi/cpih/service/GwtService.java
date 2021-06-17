package com.citi.cpih.service;

import com.citi.cpih.dto.ResponseDTO;
import com.citi.cpih.dto.UserDTO;

/**
 * @author jruizg citi.com.mx
 */
public interface GwtService {
	
	ResponseDTO getInternetBalanceV4(UserDTO userDTO);
    
}