package com.test.userlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.userlogin.exception.UserLoginException;
import com.test.userlogin.request.UserRegistrationDto;
import com.test.userlogin.service.RegistrationService;

import jakarta.validation.Valid;

//Includes the Response entity for Registering the User.

// Create a Rest Controller
@RequestMapping("/api/v1/")
@RestController
//Rest Controller for Registration, creates a RegistrationService Object
public class RegistrationController {
	private final RegistrationService registrationService;

//Constructor Injection
	@Autowired
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

//Create A Response entity for registering the user
	@PostMapping("register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto)
			throws UserLoginException {

		String response = registrationService.registerUser(userRegistrationDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}