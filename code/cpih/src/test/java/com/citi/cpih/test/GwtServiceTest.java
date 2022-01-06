package com.citi.cpih.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.citi.cpih.config.CaptiveTestConfig;
import com.citi.cpih.config.TestConfig;
import com.citi.cpih.dto.ResponseDTO;
import com.citi.cpih.dto.UserDTO;
import com.citi.cpih.service.GwtService;
import com.citi.cpih.util.Constants;

/*
* @author Jorge Ruiz
*/
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfig.class, CaptiveTestConfig.class }, locations = { "file:src/test/resources/datasourceTest.xml" })
@TestPropertySource(locations = {
		"file:src/test/resources/application.properties",
		"file:src/test/resources/gwt.properties",
		"file:src/test/resources/spr.properties"})
@SpringBootTest
public class GwtServiceTest {
	
	@Autowired
    private GwtService gwtService;

	@Test
	public void getInternetBalanceV4Test() {
		UserDTO user = new UserDTO();
		user.setMsisdn("525579035290");
		user.setSubscriptionType(Constants.CODE_POSTPAID);
		
		ResponseDTO responseDTO = this.gwtService.getInternetBalanceV4(user);
		assertEquals(0, responseDTO.getCode().intValue());
	}
	
	@Test
	public void getCloudInfoTest() {
		UserDTO user = new UserDTO();
		user.setMsisdn("525579035290");
		user.setSubscriptionType(Constants.CODE_POSTPAID);
		
		ResponseDTO responseDTO = this.gwtService.getCloudInfo(user);
		assertEquals(0, responseDTO.getCode().intValue());
	}
	
}