package com.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class Home {

	@RequestMapping(method = RequestMethod.GET,path = "/")
	public String home(HttpServletRequest httpServletRequest) {
		System.out.println("This is home page api...");
		System.out.println("Session Id : " + httpServletRequest.getSession().getId());
		return "home " + httpServletRequest.getSession().getId() ;
	}
}
