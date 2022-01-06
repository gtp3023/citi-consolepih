package com.citi.cpih.service;

import com.citi.cpih.dto.UserSprDTO;

public interface SprService {
		
	UserSprDTO getProfile(String msisdn);
		
}