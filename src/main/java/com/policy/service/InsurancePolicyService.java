package com.policy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policy.entity.InsurancePolicy;
import com.policy.repository.InsurancePolicyRepo;

@Service
public class InsurancePolicyService {

	private InsurancePolicyRepo insurancePolicyRepo;
	
	@Autowired
	public InsurancePolicyService(InsurancePolicyRepo insurancePolicyRepo) {
		this.insurancePolicyRepo = insurancePolicyRepo;
	}
	
	public List<InsurancePolicy> fetchAllPolicy(){
		return insurancePolicyRepo.findAll();
	}
	
	public Optional<InsurancePolicy> findPolicyById(Long id) {
		return insurancePolicyRepo.findById(id);
	}
	
	public InsurancePolicy saveInsurancePolicy(InsurancePolicy insurancePolicy) {
		return insurancePolicyRepo.save(insurancePolicy);
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
