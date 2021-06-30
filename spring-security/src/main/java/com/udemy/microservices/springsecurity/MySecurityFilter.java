package com.udemy.microservices.springsecurity;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MySecurityFilter implements Filter {

	//Other types of filters: 
	//- extends GenericFilterBean
	//- extends OncePerRequestFilter
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("BEFORE");
		chain.doFilter(request, response);
		System.out.println("AFTER");
	}

}
