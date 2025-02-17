package com.policy.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.policy.entity.User;
import com.policy.repository.UserRepo;

@SpringBootTest
public class UserServiceTest {
	
	@Mock
    private UserRepo userRepository;
	
	@InjectMocks
    private UserService userService;
	
	@Test
    public void testGetUserByUsername() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("pass");
        user.setRole("USER");
        
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        boolean result = userService.userExist("testuser");
        assertTrue(result);
    }
	

}
