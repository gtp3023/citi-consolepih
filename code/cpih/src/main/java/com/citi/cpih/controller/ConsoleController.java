package com.citi.cpih.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.citi.cpih.dto.UserDTO;
import com.citi.cpih.service.ConsoleService;

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
	
	@PostMapping("/search")
	public ModelAndView search(HttpServletRequest httpRequest,
			@RequestParam(value = "msisdn", required = true) String msisdn) {
		ModelAndView modelAndView = new ModelAndView("consoleDetail");
		logger.info("");
		
		UserDTO userDetail = this.consoleService.search(msisdn);
		modelAndView.addObject("userDetail", userDetail);
		
		return modelAndView;
	}
	
}