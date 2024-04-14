package edu.miu.custom.exception;

import org.springframework.http.HttpStatus;

public class EmptyFieldException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private HttpStatus httpStatus;
	
	public EmptyFieldException(String errorMessage, HttpStatus httpStatus) {
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
