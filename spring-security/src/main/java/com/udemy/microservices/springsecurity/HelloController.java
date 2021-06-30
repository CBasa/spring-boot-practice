package com.udemy.microservices.springsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Spring Security!!";
	}
	
	@GetMapping("/bye")
	public String bye() {
		return "go away!";
	}

}
