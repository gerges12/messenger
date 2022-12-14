package com.example.messanger.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.example.messanger.entity.User;

	@Repository
	public interface UserRepository extends JpaRepository<User, Long> {
		
	    Optional<User> findByEmail(String email);
	    
	    Optional<User> findByUsername(String username);
	    
	    Optional<User> findByUsernameOrEmail(String username, String email);
	    Boolean existsByUsername(String username);
	    Boolean existsByEmail(String email);


		

}
