package com.policy.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.policy.controller.InsurancePolicyController;
import com.policy.entity.InsurancePolicy;
import com.policy.repository.InsurancePolicyRepo;
import com.policy.service.InsurancePolicyService;

@SpringBootTest
public class InsurancePolicyControllerTest {
	
	@Autowired
	private InsurancePolicyService service;
	
	@MockitoBean
	private InsurancePolicyRepo repo;
	
	@Test
	void findAllTest() {
		when(repo.findAll()).thenReturn(List.of(new InsurancePolicy(101l, "Policy1", "Name1", BigDecimal.ONE, 30l)));
		assertEquals(1, service.fetchAllPolicy().size());
	}
	
	@Test
	void findPolicyByIdTest() {
		InsurancePolicy policy = new InsurancePolicy(101l, "Policy1", "Name1", BigDecimal.ONE, 30l);
		when(repo.findById(101l)).thenReturn(Optional.of(policy));
		assertEquals(policy, service.findPolicyById(101l).get());
		
	}
	
	@Test
	void updateInsurancePolicyTest() {
		InsurancePolicy policy = new InsurancePolicy(101l, "Policy1", "Name1", BigDecimal.ONE, 30l);
		when(repo.save(policy)).thenReturn(policy);
		when(repo.findById(101l)).thenReturn(Optional.of(policy));
		assertEquals(policy, service.updateInsurancePolicy(101l, policy));
	}
	
	
	
}
