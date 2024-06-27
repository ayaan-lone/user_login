package com.test.userlogin.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.test.userlogin.dao.UserRepository;
import com.test.userlogin.entity.Users;
import com.test.userlogin.exception.UserLoginException;
import com.test.userlogin.request.ForgotPasswordDto;
import com.test.userlogin.request.ResetPasswordDto;
import com.test.userlogin.service.ForgotPasswordService;
import com.test.userlogin.util.ConstantsUtil;

@Service
public class ForgotPasswordServiceImplementaiton implements ForgotPasswordService {

	private final UserRepository userRepository;

	@Autowired
	public ForgotPasswordServiceImplementaiton(UserRepository userRepository, ModelMapper modelMapper) {

		super();
		this.userRepository = userRepository;
	}

	@Override
	public String generateOTP(ForgotPasswordDto forgetPasswordDto) throws UserLoginException {
		// TODO Auto-generated method stub

		Optional<Users> optionalUser = userRepository.findByUsername(forgetPasswordDto.getUsername());
		if (optionalUser.isEmpty()) {
			throw new UserLoginException(HttpStatus.NOT_FOUND, ConstantsUtil.USER_NOT_AVAILABLE);
		}

		Users user = optionalUser.get();
		String otp = String.valueOf(new Random().nextInt(900000) + 100000);
		user.setOtp(otp);
		user.setOptGenerationTime(LocalDateTime.now());
		user.setVerifiedOtp(false);
		userRepository.save(user);

		return otp;
	}

	@Override
	public String resetPassword(ResetPasswordDto resetPasswordDto) throws UserLoginException {
	    
	    Optional<Users> userOptional = userRepository.findByUsername(resetPasswordDto.getUsername());
	    
	    if (userOptional.isPresent()) {
	        Users user = userOptional.get();
	        
	        // Check if OTP matches
	        if (resetPasswordDto.getOtp().equals(user.getOtp())) {
	            // Check if OTP is within validity period (5 minutes)
	            LocalDateTime otpGenerationTime = user.getOptGenerationTime();
	            LocalDateTime currentTime = LocalDateTime.now();
	            long minutesElapsed = ChronoUnit.MINUTES.between(otpGenerationTime, currentTime);
	            
	            if (minutesElapsed <= 5) {
	                // Set the new password
	                user.setPassword(resetPasswordDto.getNewPassword());
	                userRepository.save(user);
	                return "The Password has been set"; // Return success message after saving
	            } else {
	                throw new UserLoginException(HttpStatus.BAD_REQUEST, ConstantsUtil.OTP_EXPIRED);
	            }
	        } else {
	            throw new UserLoginException(HttpStatus.BAD_REQUEST, ConstantsUtil.OTP_IS_INCORRECT);
	        }
	    } else {
	        throw new UserLoginException(HttpStatus.NOT_FOUND, ConstantsUtil.USER_NOT_AVAILABLE);
	    }
	}


}
