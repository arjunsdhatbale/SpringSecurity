package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.main.model.UserPrinciples;
import com.main.model.Users;
import com.main.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
 
	@Autowired
	private UserRepo userRepo; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    if (username == null || username.isEmpty()) {
	        throw new UsernameNotFoundException("Username is empty or null");
	    }
	    System.out.println("Searching for user with username: " + username);
	    Users user = userRepo.findByusername(username);
	    
	    if (user == null) {
	        System.out.println("User not found in database");
	        throw new UsernameNotFoundException("User not found");
	    }
	    return new UserPrinciples(user);
	}


}
