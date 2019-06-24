package com.ox.bank.Exception;

public class LoanException extends Exception {
	private String message;

	public LoanException(String message) {
		super(message);
	}
	

}
