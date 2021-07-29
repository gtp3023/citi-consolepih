package com.citi.cpih;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.citi.cpih.ws.HttpClientPoolManager;
import com.citi.cpih.ws.HttpClientPoolManagerImpl;

/**
 * @author jorge.ruiz citi.com.mx
 */
@PropertySource({
	"file:${cpih.server.path.conf}/application.properties",
	"file:${cpih.server.path.conf}/gwt.properties",
	"file:${cpih.server.path.conf}/spr.properties",
	"file:${cpih.server.path.conf}/tango.properties"
})
@SpringBootApplication
public class ServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServerApplication.class);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
            
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource);
        return validatorFactoryBean;
    }
    
    @Bean
    public HttpClientPoolManager httpClientPoolManager() {
    	return new HttpClientPoolManagerImpl();
    }
    
}