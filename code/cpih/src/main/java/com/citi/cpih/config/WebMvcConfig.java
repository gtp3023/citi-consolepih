package com.citi.cpih.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.citi.cpih.interceptor.LogInterceptor;

/**
 * @author jorge.ruiz citi.com.mx
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Value("${cpih.server.path.conf}")
	private String pathConf;
		
	@Bean
	public LogInterceptor logInterceptor() {
	    return new LogInterceptor();
	}
		
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("file:" + this.pathConf + "/messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor()).excludePathPatterns(new String[] { "/", "/s/*"}).addPathPatterns(new String[] { "/confirm", "/terms", "/buy" });
	}
		
}