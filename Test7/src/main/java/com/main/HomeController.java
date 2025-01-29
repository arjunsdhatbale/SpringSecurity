package com.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

//	@GetMapping("/")
//	public String home(HttpServletRequest httpServletRequest) {
//		System.out.println("This is hoem controller ");
//		System.out.println("Session id : " + httpServletRequest.getSession().getId());
//		return "Wellcome to Arjun " + httpServletRequest.getSession().getId();
//	}
	
	@GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        System.out.println("This is home controller");
        System.out.println("Session ID: " + request.getSession().getId());

        // Pass session ID to the view
        model.addAttribute("sessionId", request.getSession().getId());

        return "home"; // This will map to /WEB-INF/views/home.jsp
    }
	
}
