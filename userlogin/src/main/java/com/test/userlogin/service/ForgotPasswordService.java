package com.test.userlogin.service;

import com.test.userlogin.entity.Users;
import com.test.userlogin.exception.UserLoginException;
import com.test.userlogin.request.ForgotPasswordDto;
import com.test.userlogin.request.ResetPasswordDto;

public interface ForgotPasswordService {


//    boolean verifyOTP(String username, String otp) throws UserLoginException;

	String generateOTP(ForgotPasswordDto forgetPasswordDto) throws UserLoginException;
	String resetPassword(ResetPasswordDto resetPasswordDto) throws UserLoginException;
}
