package com.test.userlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.userlogin.exception.UserLoginException;
import com.test.userlogin.request.ForgotPasswordDto;
import com.test.userlogin.request.ResetPasswordDto;
import com.test.userlogin.service.ForgotPasswordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ForgotPasswordController {

	private ForgotPasswordService forgotPasswordService;

	@Autowired
	public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
		this.forgotPasswordService = forgotPasswordService;
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<String> generateOTP(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto)
			throws UserLoginException {
		// Generate OTP and send it to the user
		String generatedOTP = forgotPasswordService.generateOTP(forgotPasswordDto);
		// Ideally, you would send the OTP via email or SMS here
		// For demonstration, let's return it directly in the response
		return ResponseEntity.status(HttpStatus.OK).body("Generated OTP: " + generatedOTP);
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto)
			throws UserLoginException {
		String response = forgotPasswordService.resetPassword(resetPasswordDto);
		return ResponseEntity.ok(response);
	}

}
