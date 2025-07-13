package com.main.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.Enum.RoleMaster;
import com.main.config.ApiResponse;
import com.main.config.AuthResponse;
import com.main.module.Users;
import com.main.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTService jwtService; 
	 
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	
	
	public Users register(Users user) {
		 
		Users users = new Users(); 
		
		users.setUserName(user.getUserName());
		users.setEmail(user.getEmail());
		users.setPassword(encoder.encode(user.getPassword()));
		users.setRole(user.getRole());
		
		return this.userRepository.save(users);
		
	
		
	}



	public String verify(Users user) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUserName());
		}else {
			return "failed";
		}
		
		 
	}


	public List<Users> getAllUsers() {

		
		return this.userRepository.findAll();
	}



	public boolean existsByEmail(String email) {

		return this.userRepository.findByEmail(email).isPresent();
	}



	public boolean existByUserName(String userName) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUserName(userName).isPresent();
	}
	
	
}
