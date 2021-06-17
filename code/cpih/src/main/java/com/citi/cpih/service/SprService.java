package com.citi.cpih.service;

import com.citi.cpih.dto.UserDTO;

public interface SprService {
		
	UserDTO getProfile(String msisdn);
		
}