package com.test.userlogin.exception;

import org.springframework.http.HttpStatus;

public class UserLoginException extends Exception {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

	public UserLoginException() {
		super();
	}

	public UserLoginException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
