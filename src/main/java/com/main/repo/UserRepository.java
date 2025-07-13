package com.main.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.module.Users;

 

public interface UserRepository extends JpaRepository<Users, Long>{

	Optional<Users> findByUserName(String username);
	Optional<Boolean> findByEmail(String email);

}
