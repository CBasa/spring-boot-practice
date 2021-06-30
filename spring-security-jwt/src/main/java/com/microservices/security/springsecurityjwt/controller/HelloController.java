package com.microservices.security.springsecurityjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.security.springsecurityjwt.config.MyUserDetailsService;
import com.microservices.security.springsecurityjwt.model.Request;
import com.microservices.security.springsecurityjwt.model.Response;
import com.microservices.security.springsecurityjwt.util.JwtUtil;

@RestController
public class HelloController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthToken(@RequestBody Request request) throws Exception {
		
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password",e);
		}
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getUsername());
	
		final String token = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new Response(token));
	
	}

}
