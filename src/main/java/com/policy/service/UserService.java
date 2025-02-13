package com.policy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.policy.entity.JwtAuthenticationResponse;
import com.policy.entity.User;
import com.policy.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public void registerUser(User user) {
		userRepo.save(user);
	}

	public JwtAuthenticationResponse authenticate(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
