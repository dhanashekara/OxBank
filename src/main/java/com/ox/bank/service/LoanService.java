package com.ox.bank.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

//import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ox.bank.Exception.LoanException;
import com.ox.bank.entity.Customer;
import com.ox.bank.entity.Loan;
import com.ox.bank.entity.Officer;
import com.ox.bank.repository.CustomerRepository;
import com.ox.bank.repository.LoanRepository;
import com.ox.bank.repository.ManagerRepository;

@Service
public class LoanService {

	@Autowired
	LoanRepository loanRepo;

	@Autowired
	ManagerRepository officerRepo;
	
	@Autowired
	CustomerRepository customerRepo;

	public String createLoan(Loan loan) {

		try {

			if (loanRepo.save(loan) != null)
				return "Loan Created!!Pending for Approval";
			else
				return "Loan not created!! Please contact support team";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// public String loanApproval(Customer customer, long officerId) throws
	// LoanException {
	public String loanApproval(long loanId) throws LoanException {
		Customer customer = null;
		// age calculation
		LocalDate currentDate = LocalDate.now();
		// System.out.println("date"+currentDate);
		// Customer cust = customerRepo.findById(customer.getId()).get();
		Loan loan = loanRepo.findById(loanId).get();
		long customerId = loan.getCustomerId();
		long loanId1 = loan.getOfficerId();
		if (!Objects.isNull(customerId)) {
			customer = customerRepo.findById(customerId).get();

		}
		// int age = Years.yearsBetween(customer.getDob(), currentDate);
//		int age = Period.between(cust.getDob(), currentDate).getYears();
		int age = Period.between(customer.getDob(), currentDate).getYears();
		// int age = 26;

		Loan loan2 = loanRepo.findBycustomerId(customer.getId());
		if (customer.getCreditScore() > 900) {
			if (customer.getWorkExperience() >= 24) {
				if (age >= 24 && age <= 50) {

					loan.setLoanStatus("Approved");

					if (loanRepo.save(loan) != null)
						return "Loan Approved!!";
					else
						return "Something went wrong!!";

				} else {
					// throw new LoanException("Age criteria is not matching");
					loan.setLoanStatus("Rejected");
					return "Age criteria is not matching!!";
				}
			} else {
				// throw new LoanException("Work experience is less!!");
				loan.setLoanStatus("Rejected");
				return "Work experience is less!!";
			}
		} else {
			// throw new LoanException("Credit Score is less!!");
			loan.setLoanStatus("Rejected");
			return "Credit Score is less!!";
		}

	}
	/*
	 * public void deleteLoan(@PathVariable long loanId, long officerId) { Loan loan
	 * = new Loan(); loan = loanRepo.findById(loanId).get(); Officer officer =
	 * officerRepo.findById(officerId).get(); String level = officer.getLevel();
	 * if(level.equals("Manager")) { if(loan!=null &&
	 * loanId==loan.getLoanAccountNumber()) { loanRepo.deleteByLoanId(loanId); }else
	 * { "Please contact to Manager"; }
	 * 
	 * } }
	 */

}
