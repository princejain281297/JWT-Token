package com.policy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policy.entity.JwtAuthenticationResponse;
import com.policy.entity.User;
import com.policy.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> userRegister(@RequestBody User user){
		userService.registerUser(user);
		return new ResponseEntity<String>("User Registered Successfully", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody User user) {
        return userService.authenticate(user);
    }

}
