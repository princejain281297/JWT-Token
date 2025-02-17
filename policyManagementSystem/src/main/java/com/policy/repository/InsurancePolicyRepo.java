package com.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.policy.entity.InsurancePolicy;

@Repository
public interface InsurancePolicyRepo extends JpaRepository<InsurancePolicy, Long> {
	
	
	

}
