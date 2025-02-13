package com.policy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policy.dto.AuthRequest;
import com.policy.entity.User;
import com.policy.service.JwtService;
import com.policy.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> userRegister(@RequestBody User user){
		if(userService.userExist(user.getUsername())) {
			return new ResponseEntity<String>("User Exist...", HttpStatus.ALREADY_REPORTED);
		}
		userService.registerUser(user);
		return new ResponseEntity<String>("User Registered Successfully", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
