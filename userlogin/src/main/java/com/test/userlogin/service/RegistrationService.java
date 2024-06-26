package com.test.userlogin.service;

import com.test.userlogin.request.UserRegistrationDto;


public interface RegistrationService {

	String registerUser(UserRegistrationDto userRegistrationRequestDto);

}
