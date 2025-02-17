package com.policy.entity;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "insurancepolicy")
@Entity
public class InsurancePolicy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "policyName")
	private String policyName;
	
	@Column(name = "policyType")
	private String policyType;
	
	@Column(name = "premiumAmount")
	private BigDecimal premiumAmount;
	
	@Column(name = "userId")
	private Long userId;
	
	public InsurancePolicy() {
		super();
	}

	public InsurancePolicy(String policyName, String policyType, BigDecimal premiumAmount, Long userId) {
		super();
		this.policyName = policyName;
		this.policyType = policyType;
		this.premiumAmount = premiumAmount;
		this.userId = userId;
	}
	
	public InsurancePolicy(String policyName, String policyType, BigDecimal premiumAmount) {
		super();
		this.policyName = policyName;
		this.policyType = policyType;
		this.premiumAmount = premiumAmount;
	}

	public InsurancePolicy(Long id, String policyName, String policyType, BigDecimal premiumAmount, Long userId) {
		super();
		this.id = id;
		this.policyName = policyName;
		this.policyType = policyType;
		this.premiumAmount = premiumAmount;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public BigDecimal getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(BigDecimal premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "InsurancePolicy [id=" + id + ", policyName=" + policyName + ", policyType=" + policyType
				+ ", premiumAmount=" + premiumAmount + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, policyName, policyType, premiumAmount, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InsurancePolicy other = (InsurancePolicy) obj;
		return Objects.equals(id, other.id) && Objects.equals(policyName, other.policyName)
				&& Objects.equals(policyType, other.policyType) && Objects.equals(premiumAmount, other.premiumAmount)
				&& Objects.equals(userId, other.userId);
	}
	
	

}
