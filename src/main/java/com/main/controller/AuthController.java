package com.main.controller;

 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.config.ApiResponse;
import com.main.config.AuthResponse;
import com.main.module.Users;
import com.main.service.JWTService;
import com.main.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private UserService userService; 
	
	@Autowired
	private JWTService jwtService; 
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody Users user) {
		try {
			logger.info("Request receive to Register User...");
			if(this.userService.existsByEmail(user.getEmail())) {
				return ResponseEntity.badRequest().body(ApiResponse.error("Email is already in use."));
			}
			
			if(this.userService.existByUserName(user.getUserName())) {
				return ResponseEntity.badRequest().body(ApiResponse.error("UserName is already in use."));
			}
			
			userService.register(user);
			String token = this.jwtService.generateToken(saveUser.getUserName(), saveUser.getRole().name(), saveUser.getId());
			
			AuthResponse response = new AuthResponse(
					token,
					saveUser.getId(),
					saveUser.getUserName(),
					saveUser.getEmail(),
					saveUser.getRole().name());
		 
	        return ResponseEntity.status(HttpStatus.CREATED)
	        		.body(ApiResponse)
			return ResponseEntity.ok("User Register successfully.");	
		}catch(Exception e) {
			logger.error("Error registering user : {}", e.getMessage());
			return ResponseEntity.badRequest().body("Error registering user : " + e.getMessage());
		}
		
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<Users>> getAllUsers(){
		logger.info("Requist receive to get List Of all Users.");
		List<Users> users = this.userService.getAllUsers();
		return new ResponseEntity<List<Users>>(users,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		logger.info("Request receive to LogIn User...");
		System.out.print(user);
		return userService.verify(user);
	}
	
	
}
