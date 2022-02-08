package com.citi.cpih.test;

import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.citi.cpih.config.TestConfig;
import com.citi.cpih.dto.UserSprDTO;
import com.citi.cpih.service.impl.SprServiceImpl;

@ComponentScan(basePackages = "com.citi.cpih")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class SprServiceTest {
	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	private SprServiceImpl sprService;

	@Test
	@SuppressWarnings("unused")
	public void getProfileSpr() {
		try {
			sprService = new SprServiceImpl();
			String msisdn = "5554004495";
			
			UserSprDTO user = sprService.getProfile(msisdn);

			assertNotNull(sprService);
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
