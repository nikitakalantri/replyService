package com.beta.replyservice.exception;

/**
 * Response in case of Error
 */
public class ApiError {

    private int statusCode;
    private String errorMessage;

    public ApiError(int status, String message) {
        this.statusCode = status;
        this.errorMessage = message;
    }

	public int getStatusCode() {
		return statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}