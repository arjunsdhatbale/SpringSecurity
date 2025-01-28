package com.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

	@GetMapping("/")
	public String home(HttpServletRequest httpServletRequest) {
		System.out.println("This is hoem controller ");
		System.out.println("Session id : " + httpServletRequest.getSession().getId());
		return "Wellcome to Arjun " + httpServletRequest.getSession().getId();
	}
	
	
	
}
