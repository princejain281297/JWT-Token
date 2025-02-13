package com.policy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policy.entity.InsurancePolicy;
import com.policy.service.InsurancePolicyService;

@RestController
@RequestMapping("/api/policies")
public class InsurancePolicyController {

	private InsurancePolicyService insurancePolicyService;
	
	@Autowired
	public InsurancePolicyController(InsurancePolicyService insurancePolicyService) {
		this.insurancePolicyService = insurancePolicyService;
	}
	
	@GetMapping
	public ResponseEntity<List<InsurancePolicy>> fetchAllPolicy(){
		List<InsurancePolicy> insurancePolicyList = insurancePolicyService.fetchAllPolicy();
		if(insurancePolicyList.size() > 0)
			return new ResponseEntity<List<InsurancePolicy>>(insurancePolicyList, HttpStatus.OK);
		return new ResponseEntity<List<InsurancePolicy>>(insurancePolicyList, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InsurancePolicy> getInsurancePolicyById(@PathVariable Long id){
		Optional<InsurancePolicy> insurancePolicy = insurancePolicyService.findPolicyById(id);
		return insurancePolicy.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
    public ResponseEntity<InsurancePolicy> createInsurancePolicy(@RequestBody InsurancePolicy insurancePolicy) {
		InsurancePolicy createdInsurancePolicy = insurancePolicyService.saveInsurancePolicy(insurancePolicy);
        return new ResponseEntity<>(createdInsurancePolicy, HttpStatus.CREATED);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<InsurancePolicy> updateUser(@PathVariable Long id, @RequestBody InsurancePolicy userDetails) {
		InsurancePolicy updatedInsurancePolicy = insurancePolicyService.updateInsurancePolicy(id, userDetails);
        if (updatedInsurancePolicy != null) {
            return new ResponseEntity<>(updatedInsurancePolicy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        boolean isDeleted = insurancePolicyService.deletePolicyById(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
}
