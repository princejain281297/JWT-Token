package com.policy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policy.entity.InsurancePolicy;
import com.policy.entity.User;
import com.policy.filter.JwtAuthFilter;
import com.policy.repository.InsurancePolicyRepo;
import com.policy.repository.UserRepo;

@Service
public class InsurancePolicyService {

	private InsurancePolicyRepo insurancePolicyRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	public InsurancePolicyService(InsurancePolicyRepo insurancePolicyRepo) {
		this.insurancePolicyRepo = insurancePolicyRepo;
	}
	
	public List<InsurancePolicy> fetchAllPolicy(){
		return insurancePolicyRepo.findAll();
	}
	
	public Optional<InsurancePolicy> findPolicyById(Long id) {
		Long userId = getUserIdFromUser();
		Optional<InsurancePolicy> insurancePolicy = insurancePolicyRepo.findById(id);
		if(insurancePolicy.isPresent() && userId == insurancePolicy.get().getUserId())
			return insurancePolicy;
		return Optional.empty();
	}
	
	public InsurancePolicy saveInsurancePolicy(InsurancePolicy insurancePolicy) {
		Long userId = getUserIdFromUser();
		insurancePolicy.setUserId(userId);
		return insurancePolicyRepo.save(insurancePolicy);
	}
	
	private Long getUserIdFromUser() {
        String username = JwtAuthFilter.getUsername();
        Optional<User> user = userRepo.findByUsername(username);
		return user.get().getId();
	}

	public InsurancePolicy updateInsurancePolicy(Long id, InsurancePolicy policyDetails) {
		Optional<InsurancePolicy> optionalUser = insurancePolicyRepo.findById(id);
        if (optionalUser.isPresent()) {
        	InsurancePolicy existingInsurancePolicy = optionalUser.get();
        	existingInsurancePolicy.setPolicyName(policyDetails.getPolicyName());
        	existingInsurancePolicy.setPolicyType(policyDetails.getPolicyType());
        	existingInsurancePolicy.setPremiumAmount(policyDetails.getPremiumAmount());
            existingInsurancePolicy.setUserId(policyDetails.getUserId());
            return insurancePolicyRepo.save(existingInsurancePolicy);
        } else {
            // Handle the case where the InsurancePolicy is not found
            return null;
        }
	}
	
	public boolean deletePolicyById(Long id) {
		if (insurancePolicyRepo.existsById(id)) {
			insurancePolicyRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
	}
	
}
