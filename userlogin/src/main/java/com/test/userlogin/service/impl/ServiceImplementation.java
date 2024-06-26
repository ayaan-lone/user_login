package com.test.userlogin.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.userlogin.dao.UserRepository;
import com.test.userlogin.entity.Users;
import com.test.userlogin.request.UserRegistrationDto;
import com.test.userlogin.service.RegistrationService;

@Service
public class ServiceImplementation implements RegistrationService {

	private final UserRepository registerUserRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public ServiceImplementation(UserRepository registerUserRepository, ModelMapper modelMapper) {
		super();
		this.registerUserRepository = registerUserRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public String registerUser(UserRegistrationDto userRegistrationRequestDto) {
		Users user = modelMapper.map(userRegistrationRequestDto, Users.class);
		registerUserRepository.save(user);
		return "User has been created.";
	}

}
