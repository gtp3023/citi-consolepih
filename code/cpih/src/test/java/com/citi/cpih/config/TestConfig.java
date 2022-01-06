package com.citi.cpih.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.citi.cpih.service.GwtService;
import com.citi.cpih.service.impl.GwtServiceImpl;
import com.citi.cpih.util.log.LegacyTransLog;

/*
* @author Jorge Ruiz
*/
@TestConfiguration
public class TestConfig {
	
	@Bean
	public GwtService gwtService() {
		return new GwtServiceImpl();
	}
	
	@Bean
	public LegacyTransLog legacyTransLog() {
		return new LegacyTransLog();
	}
	
}