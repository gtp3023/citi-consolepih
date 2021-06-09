package com.citi.cpih.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.citi.cpih.util.Constants;

/**
 * @author jorge.ruiz citi.com.mx
 */
@Controller
public class LoginController {
	
	@GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", Constants.INCORRECT_CREDENTIALS);
        }
        
        if (logout != null) {
            return "redirect:/login";
        }
        
        return "login";
    }
	
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
	
}