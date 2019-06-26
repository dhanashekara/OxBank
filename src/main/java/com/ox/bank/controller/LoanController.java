package com.ox.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ox.bank.Exception.LoanException;
import com.ox.bank.entity.Loan;
import com.ox.bank.service.LoanService;

@RestController
public class LoanController {

	@Autowired
	LoanService loanService;

	@PostMapping("/createLoan")
	public ResponseEntity<String> createLoan(@RequestBody Loan loan) {

		if (loanService.createLoan(loan).equalsIgnoreCase("Loan Created!!Pending for Approval"))
			return new ResponseEntity<String>("Loan Created!!Pending for Approval", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Loan not created!! Please contact support team", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/loanApproval/{loanId}")
	public ResponseEntity<String> loanApproval(@PathVariable long loanId)
			throws LoanException {

		String status = loanService.loanApproval(loanId);

		if (status.equalsIgnoreCase("Loan Approved!!"))
			return new ResponseEntity<String>(status, HttpStatus.OK);
		else
			return new ResponseEntity<String>(status, HttpStatus.BAD_REQUEST);

	}
	
	  @DeleteMapping("/deleteLoan/{loanId}/{officerId}")
	  public String deleteLoan(@PathVariable long loanId, long officerId)throws LoanException 
		{
		  if(loanService.deleteLoan(loanId, officerId).equalsIgnoreCase("Loan successfully deleted")) {
			  return "Loan successfully deleted";
		}else 
			return "Loan not deleted";
		
}
}
