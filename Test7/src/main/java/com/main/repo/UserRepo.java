package com.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{

	Users findByusername(String username);

 	
 	
}
