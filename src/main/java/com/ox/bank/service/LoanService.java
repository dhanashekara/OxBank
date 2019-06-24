package com.ox.bank.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

//import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ox.bank.Exception.LoanException;
import com.ox.bank.entity.Customer;
import com.ox.bank.entity.Loan;
import com.ox.bank.repository.CustomerRepository;
import com.ox.bank.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	LoanRepository loanRepo;

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

	public String loanApproval(Customer customer, long officerId) throws LoanException {

		// age calculation
	//	LocalDate currentDate = LocalDate.now();
		// int age = Years.yearsBetween(customer.getDob(), currentDate);
	//	int age = Period.between(customer.getDob(), currentDate).getYears();
		int age = 26;
		Customer cust = customerRepo.findById(customer.getId()).get();
		
		if (cust.getCreditScore() > 900) {
			if (cust.getWorkExperience() >= 24) {
				if (age >= 24 && age <= 50) {

					Loan loan = loanRepo.findBycustomerId(cust.getId());
					loan.setLoanStatus("Approved");

					if (loanRepo.save(loan) != null)
						return "Loan Approved!!";
					else
						return "Something went wrong!!";

				} else {
					// throw new LoanException("Age criteria is not matching");
					return "Age criteria is not matching!!";
				}
			} else {
				// throw new LoanException("Work experience is less!!");
				return "Work experience is less!!";
			}
		} else {
			// throw new LoanException("Credit Score is less!!");
			return "Credit Score is less!!";
		}

	}

}
