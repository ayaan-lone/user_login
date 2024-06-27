package com.test.userlogin.service;

import com.test.userlogin.exception.UserLoginException;
import com.test.userlogin.request.UserRegistrationDto;


//Declared an Interface for the Registration Service
public interface RegistrationService {

//Created an Abstract String Method named registerUser that will take userRegistrationDto as the param.

	String registerUser(UserRegistrationDto userRegistrationRequestDto) throws UserLoginException;

}
