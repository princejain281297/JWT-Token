package com.policy.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.policy.controller.UserController;
import com.policy.dto.AuthRequest;
import com.policy.entity.User;
import com.policy.service.JwtService;
import com.policy.service.UserService;

@SpringBootTest
public class UserControllerTest {
	
	@Mock
    private JwtService jwtService;

	@Mock
    private AuthenticationManager authenticationManager;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	@Test
	void userRegisterTestWithUserName() {
		when(userService.userExist("user")).thenReturn(Boolean.TRUE);
		
		ResponseEntity<String> response = userController.userRegister(new User(101l, "user", "xyz", "USER_ROLE"));
		assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
		assertEquals("User Exist...", response.getBody());
	}
	
	@Test
	void userRegisterTestWithoutUserName() {
		when(userService.userExist("user")).thenReturn(Boolean.FALSE);
		
		ResponseEntity<String> response = userController.userRegister(new User(101l, "admin", "xyz", "USER_ROLE"));
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals("User Registered Successfully", response.getBody());
	}
	
	@SuppressWarnings("serial")
	@Test
	void loginTest_isNotAuthenticated() {
		AuthRequest authRequest = new AuthRequest("user", "pass");
		when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())))
			.thenReturn(new Authentication() {
				
				@Override
				public String getName() {
					return "user";
				}
				
				@Override
				public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
					
				}
				
				@Override
				public boolean isAuthenticated() {
					return false;
				}
				
				@Override
				public Object getPrincipal() {
					return null;
				}
				
				@Override
				public Object getDetails() {
					return null;
				}
				
				@Override
				public Object getCredentials() {
					return "pass";
				}
				
				@Override
				public Collection<? extends GrantedAuthority> getAuthorities() {
					return null;
				}
			});
		
		assertThrows(UsernameNotFoundException.class, () -> {
			userController.login(authRequest);
        });
		
	}
	
	

}
