package com.beta.replyservice.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String INVALID_INPUT = "Invalid input";

	/**
	 * To handle the ReplyException.
	 * 
	 * @param ex
	 * @return the Formatted Error Response.
	 */
	@ExceptionHandler({ ReplyException.class })
	public ResponseEntity<Object> handleCustomException(ReplyException ex) {
		ApiError apiError = new ApiError(ex.getStatus().value(), ex.getLocalizedMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatusCode());
	}

	/**
	 * To handle the Constraint Violation Exception.
	 * 
	 * @param ex
	 * @return the Formatted Error Response.
	 */
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleInvalidInput(ConstraintViolationException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), INVALID_INPUT);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatusCode());
	}

	/**
	 * To handle the All other Exception.
	 * @param ex
	 * @return the Formatted Error Response.
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatusCode());
	}
}
