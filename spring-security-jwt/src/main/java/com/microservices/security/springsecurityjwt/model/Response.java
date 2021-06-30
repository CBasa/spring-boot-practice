package com.microservices.security.springsecurityjwt.model;

public class Response {
	
	private final String jwt;

	public String getJwt() {
		return jwt;
	}

	public Response(String jwt) {
		super();
		this.jwt = jwt;
	}
}
