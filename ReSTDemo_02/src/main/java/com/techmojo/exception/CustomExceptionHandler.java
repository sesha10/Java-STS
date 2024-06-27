package com.techmojo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(InvalidEmployeeIdException.class)
	public ResponseEntity<ErrorResponse> method1(InvalidEmployeeIdException e) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		String message = e.getMessage();
		ErrorResponse errorResponse = new ErrorResponse(httpStatus, message);
		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);
		return responseEntity;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> method2(Exception e) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = e.getMessage();
		ErrorResponse errorResponse = new ErrorResponse(httpStatus, message);
		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse, httpStatus);
		return responseEntity;
	}
}
