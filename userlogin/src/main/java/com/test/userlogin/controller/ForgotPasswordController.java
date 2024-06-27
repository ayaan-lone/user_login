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

/*1. Forgot-Password-Generate Password is Available here
 * 2. Password Reset is Also Available here*/

@RestController
@RequestMapping("/api/v1")
public class ForgotPasswordController {

	private ForgotPasswordService forgotPasswordService;

	@Autowired
	public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
		this.forgotPasswordService = forgotPasswordService;
	}

	// User can generate the OTP For their UserName and then can go to the reset-password 
	@PostMapping("/forgot-password-generate-otp")
	public ResponseEntity<String> generateOTP(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto)
			throws UserLoginException {
		// Generate OTP and send it to the user
		
		String generatedOTP = forgotPasswordService.generateOTP(forgotPasswordDto);

		return ResponseEntity.status(HttpStatus.OK).body("Please go to the Reset Password Entity, Your Generated OTP is : " + generatedOTP);
	}
	
	//The user can use the generated OTP here to reset the Password, if wrong otp is given, it will give an error
	/*If the OTP has not yet been generated, it will say that the OTP is not yet generated
	 * if the OTP has expired....
	 * 
	 * */

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto)
			throws UserLoginException {
		String response = forgotPasswordService.resetPassword(resetPasswordDto);
		return ResponseEntity.ok(response);
	}

}
