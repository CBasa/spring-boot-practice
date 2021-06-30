package com.udemy.microservices.currencyexchangeservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.microservices.currencyexchangeservice.bean.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByUsername(String username);
	
}
