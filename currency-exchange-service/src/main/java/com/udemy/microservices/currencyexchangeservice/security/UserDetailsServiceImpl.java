package com.udemy.microservices.currencyexchangeservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udemy.microservices.currencyexchangeservice.bean.User;
import com.udemy.microservices.currencyexchangeservice.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepo userRepo; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user ==null) {
			System.out.println("User not found for: "+username);
			throw new UsernameNotFoundException("User not found for username "+username);
		} else {
//			String roles = user.getRoles().stream().map(Role::getName).collect(Collectors.joining(", ", "  Role: ", ""));
//			System.out.println("User found: "+user.getUsername()+roles);
//			String roles = user.getRoles().stream().collect(Collectors.joining(", ", "  Role: ", ""));
			System.out.println("user found: "+user.getUsername()+" Password: "+user.getPassword());
		}
		
//		org.springframework.security.core.userdetails.User user2 = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles().toArray(String::new));
		
		UserDetails userDetails = org.springframework.security.core.userdetails.User
			.withUsername(user.getUsername())
			.password(user.getPassword())
			.roles((String[]) user.getRoles().toArray()).build();
		
		return userDetails;
	}

}
