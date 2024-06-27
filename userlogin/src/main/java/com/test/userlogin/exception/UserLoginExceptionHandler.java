	package com.test.userlogin.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class UserLoginExceptionHandler {
	
	
	@ExceptionHandler(value = {UserLoginException.class})
	
	public ResponseEntity<Object> handleUserLoginException(UserLoginException userLoginException){
		return ResponseEntity.status(userLoginException.getHttpStatus()).body(userLoginException.getMessage());
	}
}
