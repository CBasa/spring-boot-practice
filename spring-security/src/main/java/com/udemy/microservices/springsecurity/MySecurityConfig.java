package com.udemy.microservices.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyAuthenticationProvider authenticationProvider;
	
	// USING INBUILT AUTHENTICATION PROVIDER
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//			
//		UserDetails user = User 
//				.withUsername("celina")
//				.password(passwordEncoder.encode("basa"))
//				.authorities("read")
//				.build();
//		
//		userDetailsService.createUser(user);
//		
//		auth.userDetailsService(userDetailsService);
//	}
	
	// USING OWN AUTHENTICATION PROVIDER
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authorizeRequests().antMatchers("/hello").authenticated();
		http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
