package com.policy.entity;


public class JwtAuthenticationResponse {
    private String token;

	public JwtAuthenticationResponse() {
		super();
	}

	public JwtAuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationResponse [token=" + token + "]";
	}
    
}
