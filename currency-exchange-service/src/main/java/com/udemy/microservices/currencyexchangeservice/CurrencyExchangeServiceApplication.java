package com.udemy.microservices.currencyexchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyExchangeServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		System.out.println(encoder.encode("basa"));
//		System.out.println(encoder.encode("basa"));
//		//$2a$10$Eu4ilW5v.t8JvKYpiMCYUeb9Y2m5pXPPDHRyytZL1J7ew9ehST7.K
		
	}

}
