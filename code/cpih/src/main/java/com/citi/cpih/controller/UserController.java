package com.citi.cpih.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.citi.cpih.model.User;
import com.citi.cpih.service.UserService;
import com.citi.cpih.util.ComboUtil;

/**
 * @author jruizg citi.com.mx
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	private UserService userService;
		
	@PostMapping()
	public ModelAndView loadPage(HttpServletRequest httpRequest) {
		ModelAndView modelAndView = new ModelAndView("user");
		modelAndView.addObject("listRole", ComboUtil.getListRoleCombo());
		return modelAndView;
	}
	
	@PostMapping("/grid")
	public ModelAndView grid(HttpServletRequest httpRequest) {
		ModelAndView modelAndView = new ModelAndView("userList");
		List<User> users = this.userService.findAll();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
		
	@PostMapping("/validateUsername")
	@ResponseBody
	public Map<String, Object> validateUserName(HttpServletRequest httpRequest, 
			@RequestParam(value = "username", required = true) String username) {
		
		Map<String, Object> modelAndViewMap = new HashMap<String, Object>();
		boolean response = this.userService.existUsername(username);
		modelAndViewMap.put("response", response);
		return modelAndViewMap;
	}
	
	@PostMapping("/findById")
	@ResponseBody
	public Map<String, Object> findById(HttpServletRequest httpRequest, 
			@RequestParam(value = "id", required = true) Long id) {
		Map<String, Object> modelAndViewMap = new HashMap<String, Object>();
			
		User response = this.userService.findById(id);
		modelAndViewMap.put("response", response);
		
		return modelAndViewMap;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest httpRequest, @RequestBody User user) {
		Map<String, Object> modelAndViewMap = new HashMap<String, Object>();
		
		boolean response = this.userService.save(user);
		modelAndViewMap.put("response", response);
		
		return modelAndViewMap;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest httpRequest, 
			@RequestParam(value = "id", required = true) Long id) {
		Map<String, Object> modelAndViewMap = new HashMap<String, Object>();
			
		boolean response = this.userService.delete(new User(id));
		modelAndViewMap.put("response", response);
			
		return modelAndViewMap;
	}
	
}