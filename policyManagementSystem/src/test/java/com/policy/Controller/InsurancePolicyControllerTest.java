package com.policy.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
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
	
	static List<InsurancePolicy> insurancePolicyList = List.of(
			new InsurancePolicy(101l, "Policy1", "Name1", BigDecimal.ONE, 1l),
			new InsurancePolicy(102l, "Policy2", "Name2", BigDecimal.ONE, 2l),
			new InsurancePolicy(103l, "Policy3", "Name3", BigDecimal.ONE, 3l),
			new InsurancePolicy(104l, "Policy4", "Name4", BigDecimal.ONE, 4l)
			);
	
	@Mock
	private InsurancePolicyRepo insurancePolicyRepo; 
	
	@InjectMocks
	private InsurancePolicyController insurancePolicyController;
	
	@Mock
	private InsurancePolicyService insurancePolicyService;
	
	@Test
	@WithMockUser(username = "user", roles = {"ADMIN"})
	public void testFindAllWithInsurancePolicy() throws Exception {
		when(insurancePolicyService.fetchAllPolicy()).thenReturn(List.of(new InsurancePolicy(101l, "Policy1", "Name1", BigDecimal.ONE, 30l)));
		
		ResponseEntity<List<InsurancePolicy>> response = insurancePolicyController.fetchAllPolicy();
	
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(List.of(new InsurancePolicy(101l, "Policy1", "Name1", BigDecimal.ONE, 30l)), response.getBody());
		
	}
	
	@Test
	@WithMockUser(username = "user", roles = {"ADMIN"})
	public void testFindAllWithoutInsurancePolicy() throws Exception {
		when(insurancePolicyService.fetchAllPolicy()).thenReturn(new ArrayList<InsurancePolicy>());
		
		ResponseEntity<List<InsurancePolicy>> response = insurancePolicyController.fetchAllPolicy();
	
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(0, response.getBody().size());
		
	}

	@Test
	@WithMockUser(username = "user", roles = {"ADMIN"})
	public void testGetInsurancePolicyById() throws Exception {
		when(insurancePolicyService.findPolicyById(insurancePolicyList.get(0).getId())).thenReturn(
				Optional.of(insurancePolicyList.get(0)));
		
		ResponseEntity<InsurancePolicy> response = insurancePolicyController.getInsurancePolicyById(insurancePolicyList.get(0).getId());
	
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void testCreateInsurancePolicy() throws Exception {
		InsurancePolicy policy = new InsurancePolicy(101l, "Policy1", "Name1", BigDecimal.ONE, 30l);
		when(insurancePolicyService.saveInsurancePolicy(policy)).thenReturn(policy);
	
		ResponseEntity<InsurancePolicy> response = insurancePolicyController.createInsurancePolicy(policy);
	
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(policy, response.getBody());
		
	}
	
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void testUpdatePolicyWithNull() throws Exception {
		InsurancePolicy policy = new InsurancePolicy(101l, "Policy1", "Name1", BigDecimal.ONE, 30l);
		
		when(insurancePolicyService.updateInsurancePolicy(101l, policy))
			.thenReturn(null);
		
		ResponseEntity<InsurancePolicy> response = insurancePolicyController.updateUser(101l, policy);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
		
	}
	
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void testUpdatePolicyWithPolicy() throws Exception {
		
		InsurancePolicy policyUpdated = new InsurancePolicy(101l, "Policy101", "Name1", BigDecimal.ONE, 30l);
		when(insurancePolicyService.updateInsurancePolicy(101l, policyUpdated)).thenReturn(policyUpdated);
		
		ResponseEntity<InsurancePolicy> response = insurancePolicyController.updateUser(101l, policyUpdated);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(policyUpdated, response.getBody());
		
	}
	
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void testDeletePolicyWithExistPolicy() throws Exception {
		
		InsurancePolicy policy = new InsurancePolicy(101l, "Policy1", "Name1", BigDecimal.ONE, 30l);
		when(insurancePolicyService.deletePolicyById(101l)).thenReturn(false);
		ResponseEntity<Void> response = insurancePolicyController.deletePolicy(101l);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
	}
	
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void testDeletePolicyWithOutExistPolicy() throws Exception {
		
		InsurancePolicy policy = new InsurancePolicy(101l, "Policy1", "Name1", BigDecimal.ONE, 30l);
		when(insurancePolicyService.deletePolicyById(101l)).thenReturn(true);
		ResponseEntity<Void> response = insurancePolicyController.deletePolicy(101l);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
	}
	
}
