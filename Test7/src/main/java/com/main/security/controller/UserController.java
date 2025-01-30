package com.main.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.security.model.Users;
import com.main.security.service.UserService;

// @RestController
@Controller
public class UserController {

	@Autowired
	private UserService userService; 
	
//	@PostMapping("/register")
//	public Users register(@RequestBody Users user) {
//		
//		return userService.register(user);
//	}
	
	@GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // This returns the register.jsp page
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@RequestBody Users user) {
        Users registeredUser = userService.register(user);
        if (registeredUser != null) {
            return "{\"message\": \"User registered successfully\"}";
        } else {
            return "{\"message\": \"Registration failed\"}";
        }
    }
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		System.out.println(user);
		return  userService.verify(user);
		
	}
	
}
