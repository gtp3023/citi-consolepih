package com.citi.cpih.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.citi.cpih.dto.UserDTO;
import com.citi.cpih.service.ConsoleService;
import com.citi.cpih.util.Constants;

/**
 * @author jruizg citi.com.mx
 */
@Controller
@RequestMapping("/console")
public class ConsoleController {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	private ConsoleService consoleService;

	@PostMapping()
	public ModelAndView loadPage(HttpServletRequest httpRequest) {
		return new ModelAndView("console");
	}
	
	@PostMapping(value="/validateUser")
	@ResponseBody
	public Map<String, Object> validateUser(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msisdn", required = true) String msisdn) {
    	
    	Map<String, Object> modelAndViewMap = new HashMap<>();
		int retVal = 0;
    	
    	try {
    		UserDTO userDetail = this.consoleService.getUserSpr(msisdn);
    		
    		if(userDetail == null) {
    			retVal = 1;
    		} else if(userDetail.getHasVpn().equals(Constants.VPN_INCORRECT)) {
    			retVal = 2;
    		} else {
    			session.removeAttribute(Constants.USER_SESSION);
    			session.setAttribute(Constants.USER_SESSION, userDetail);
    		}
		} catch (Exception e) {
			this.logger.error("Error on reset method [Exception] => Message: {}", e.getMessage());
		}
    	
    	modelAndViewMap.put("retVal", retVal);
		
		return modelAndViewMap;
    }
	
	@PostMapping("/search")
	public ModelAndView search(HttpSession session, HttpServletRequest httpRequest, 
			@RequestParam(value = "msisdn", required = true) String msisdn) {
		ModelAndView modelAndView = new ModelAndView("consoleDetail");
		logger.info("");
		
		UserDTO userSession = null != session.getAttribute(Constants.USER_SESSION) ? (UserDTO) session.getAttribute(Constants.USER_SESSION) : null;
		
		UserDTO userDetail = this.consoleService.search(msisdn, userSession);
		
		/*if(null != session.getAttribute(Constants.USER_SESSION)) {
			UserDTO userSession = (UserDTO) session.getAttribute(Constants.USER_SESSION);
			userDetail.setSubscriptionType(userSession.getSubscriptionType());
			userDetail.setSubscription(userSession.getSubscription());
			userDetail.setHasVpn(userSession.getHasVpn());
		}*/
		
		session.removeAttribute(Constants.USER_SESSION);
		session.setAttribute(Constants.USER_SESSION, userDetail);
		
		return modelAndView;
	}
	
}