package com.ox.bank.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ox.bank.entity.Loan;
import com.ox.bank.repository.LoanRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

	@InjectMocks
	LoanService loanSerive;

	@Mock
	LoanRepository loanRepo;

	@Test
	public void createLoanTest() {

		// Officer officer = new Officer(10);
		// Customer customer = new Customer(10);

		// Positive case
		Loan loan = new Loan(10, "pending", 500, 10, 10);
		Mockito.when(loanRepo.save(loan)).thenReturn(loan);
		String actualResult = loanSerive.createLoan(loan);
		assertEquals("Loan Created!!Pending for Approval", actualResult);

		// Negative case
		loan = null;
		String actualResultNegative = loanSerive.createLoan(loan);
		assertEquals("Loan not created!! Please contact support team", actualResultNegative);

	}
	
	@Test
	public void approveLoan() {
		
		
	}

}
