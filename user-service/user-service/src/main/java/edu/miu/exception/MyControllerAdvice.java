package edu.miu.exception;

import java.net.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.miu.custom.exception.EmailValidationException;
import edu.miu.custom.exception.EmptyFieldException;
import edu.miu.custom.exception.PasswordValidationException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmptyFieldException.class)
	public ResponseEntity<String> handleEmptyFields() {
		return new ResponseEntity<String>("Fields are mandatory, shouldn't be empty!", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PasswordValidationException.class)
	public ResponseEntity<String> handleEmptyBooksList() {
		return new ResponseEntity<String>(
				"Password have to contains at least 1 digit, 1 small letter, 1 big letter, 1 special char, min length 6, max length 12",
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmailValidationException.class)
	public ResponseEntity<String> handleEmailValidation() {
		return new ResponseEntity<String>("Please enter a correst email format.", HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>("Please check if you're using the appropriate request method.",
				HttpStatus.METHOD_NOT_ALLOWED);
	}
}
