package com.beta.replyservice.exception;

import org.springframework.http.HttpStatus;

public class ReplyException extends RuntimeException{

	private static final long serialVersionUID = 1394063672726449217L;
	
	private String message;
	private HttpStatus status;
	
	public ReplyException(String message, HttpStatus status){
		super(message);
		this.message = message;
		this.status= status;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
