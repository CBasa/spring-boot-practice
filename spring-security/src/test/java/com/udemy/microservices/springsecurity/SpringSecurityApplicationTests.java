package com.udemy.microservices.springsecurity;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Test
	void testPasswordEncoders() {
		System.out.println("Bcrypt: "+new BCryptPasswordEncoder().encode("password"));
		System.out.println("Pbkdf2: "+new Pbkdf2PasswordEncoder().encode("password"));
		System.out.println("Scrypt: "+new SCryptPasswordEncoder().encode("password")); //requires bouncy castle dependency
	
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());
		
		//Allows you to choose which encoder to use at runtime using the key
		System.out.println("Delegating: "+new DelegatingPasswordEncoder("bcrypt", encoders).encode("password"));
	}

}
