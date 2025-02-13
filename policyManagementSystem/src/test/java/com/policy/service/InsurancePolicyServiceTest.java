package com.policy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.policy.entity.InsurancePolicy;
import com.policy.repository.InsurancePolicyRepo;

@SpringBootTest
public class InsurancePolicyServiceTest {

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
