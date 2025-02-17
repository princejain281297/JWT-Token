package com.policy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@SpringBootTest
public class JwtServiceTest {
	
	public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

	@InjectMocks
	private JwtService jwtService;
	
	@Test
	void generateToken_ShouldGenerateValidToken() {
		String username = "john";
		String token = jwtService.generateToken(username);
		
		Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
		
		assertEquals(username, claims.getSubject());
		
	}
	
}
