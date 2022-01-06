package com.citi.cpih.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.citi.cpih.ws.HeaderHandlerResolver;

import mx.com.citi.captivecore.CaptiveCore;
import mx.com.citi.captivecore.CaptiveCoreService;

/**
 * @author Jorge Ruiz
 */
@TestConfiguration
public class CaptiveTestConfig {
	
	@Value("${gwt.wsdl}")
	private String gwtWsld;
	
	@Value("${gwt.credential.user}")
	private String gwtUser;
	
	@Value("${gwt.credential.password}")
	private String gwtPass;
	
	@Bean
	public CaptiveCore getCaptiveCoreInstance() throws MalformedURLException {
		CaptiveCoreService captiveCoreService = null;
		captiveCoreService = new CaptiveCoreService(new URL(this.gwtWsld));
		captiveCoreService.setHandlerResolver(new HeaderHandlerResolver(this.gwtUser, this.gwtPass));
		return captiveCoreService.getCaptiveCorePort();
	}
    
}