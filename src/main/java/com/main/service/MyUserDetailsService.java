package com.main.service;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.main.module.UserPrinciple;
import com.main.module.Users;
import com.main.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = this.userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
		 

		return new UserPrinciple(user);
	}

	public Users saveUser(Users user) {
		return this.userRepository.save(user);
	}

}
