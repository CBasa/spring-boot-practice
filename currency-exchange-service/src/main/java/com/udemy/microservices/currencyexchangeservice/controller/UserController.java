package com.udemy.microservices.currencyexchangeservice.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.microservices.currencyexchangeservice.bean.Response;
import com.udemy.microservices.currencyexchangeservice.bean.User;
import com.udemy.microservices.currencyexchangeservice.repo.UserRepo;
import com.udemy.microservices.currencyexchangeservice.security.UserDetailsServiceImpl;
import com.udemy.microservices.currencyexchangeservice.util.JwtUtil;

@RestController
public class UserController {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@PostMapping("/registration")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void register(@RequestBody User user) {
		User newUser = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), Set.of("ROLE_USER"));
		userRepo.save(newUser);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthToken(@RequestBody User request) throws Exception {

		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

		final String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new Response(token));

	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepo.findAll();
	}

}
