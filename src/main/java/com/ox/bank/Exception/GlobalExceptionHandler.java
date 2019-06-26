package com.ox.bank.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	// This is for handling global exception

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(new Date(), exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);

	}

	/*
	 * //This is for specific exception to handle
	 * 
	 * @ExceptionHandler(EmployeeResourceNotFoundException.class) public
	 * ResponseEntity<ErrorResponse>
	 * ResourceNotFoundExceptionHandler(EmployeeResourceNotFoundException
	 * ex,WebRequest request) { ErrorResponse errorResponse = new ErrorResponse(new
	 * Date(), ex.getMessage(), request.getDescription(false));
	 * 
	 * return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	 * 
	 * }
	 */

}