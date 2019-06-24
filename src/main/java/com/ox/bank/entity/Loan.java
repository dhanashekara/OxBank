package com.ox.bank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long loanAccountNumber;

	String loanStatus;
	long loanAmount;
	long officerId;
	long customerId;

	public Loan() {
		super();
	}

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn Officer officer;
	 * 
	 * @OneToOne
	 * 
	 * @JoinColumn Customer customer;
	 */

	public Loan(long loanAccountNumber, String loanStatus, long loanAmount, long officerId, long customerId) {
		super();
		this.loanAccountNumber = loanAccountNumber;
		this.loanStatus = loanStatus;
		this.loanAmount = loanAmount;
	}

	public long getLoanAccountNumber() {
		return loanAccountNumber;
	}

	public void setLoanAccountNumber(long loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public long getOfficerId() {
		return officerId;
	}

	public void setOfficerId(long officerId) {
		this.officerId = officerId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/*
	 * public Officer getOfficer() { return officer; }
	 * 
	 * public void setOfficer(Officer officer) { this.officer = officer; }
	 * 
	 * public Customer getCustomer() { return customer; }
	 * 
	 * public void setCustomer(Customer customer) { this.customer = customer; }
	 */

}
