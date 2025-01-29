package com.main.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.security.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{

	Users findByusername(String username);

 	
 	
}
