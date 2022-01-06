package com.citi.cpih.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.citi.cpih.ws.HeaderHandlerResolver;

import mx.com.citi.captivecore.CaptiveCore;
import mx.com.citi.captivecore.CaptiveCoreService;

/**
 * @author jorge.ruiz citi.com.mx
 */
@Configuration
public class CaptiveConfig {
	
	@Value("${gwt.wsdl}")
	private String gwtWsld;
	
	@Value("${gwt.credential.user}")
	private String credentialUser;
	
	@Value("${gwt.credential.password}")
	private String credentialPassword;
	
	@Bean
	public CaptiveCore getCaptiveCoreInstance() throws MalformedURLException {
		CaptiveCoreService captiveCoreService = null;
		captiveCoreService = new CaptiveCoreService(new URL(this.gwtWsld));
		captiveCoreService.setHandlerResolver(new HeaderHandlerResolver(this.credentialUser, this.credentialPassword));
		return captiveCoreService.getCaptiveCorePort();
	}
    
}