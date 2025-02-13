package com.policy;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.policy.controller.InsurancePolicyController;
import com.policy.entity.InsurancePolicy;
import com.policy.repository.InsurancePolicyRepo;
import com.policy.service.InsurancePolicyService;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user", roles = "ADMIN_ROLE")
class PolicyManagementSystemApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private InsurancePolicyRepo insurancePolicyRepo; 
	
	@MockitoBean
	private InsurancePolicyController insurancePolicyController;
	
	@Mock
	private InsurancePolicyService insurancePolicyService;
	
	@Test
	public void testFindAll() throws Exception {
		InsurancePolicy insurancePolicy = new InsurancePolicy();
//		insurancePolicy.setId(101l);
//		insurancePolicy.setPolicyName("Policy1");
//		insurancePolicy.setPolicyType("PolicyType1");
//		insurancePolicy.setPremiumAmount(BigDecimal.TWO);
//		insurancePolicy.setUserId(1l);
		List<InsurancePolicy> list = new ArrayList<InsurancePolicy>(); //List.of(insurancePolicy);
		
		when(insurancePolicyRepo.findAll()).thenReturn(list);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/policies"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
