package com.test.userlogin.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.test.userlogin.dao.UserRepository;
import com.test.userlogin.entity.Users;
import com.test.userlogin.exception.UserLoginException;
import com.test.userlogin.request.UserRegistrationDto;
import com.test.userlogin.service.RegistrationService;
import com.test.userlogin.util.ConstantsUtil;


//RegistrationServiceImplementation

//Created a Service named Service Implementation that Implements the Interface RegistrationService

@Service
public class ServiceImplementation implements RegistrationService {
	
	//Here we are using the User Repository that we created before.
	
	//Created UserRepository Object named registerUserRepository
	private final UserRepository registerUserRepository;
	//Created a Model Map Object for registering the users.
	private final ModelMapper modelMapper;

	//Automatic Constructor so that it injects the bean as soon as the instance of this is created 
	@Autowired
	public ServiceImplementation(UserRepository registerUserRepository, ModelMapper modelMapper) {
		//maps the user and initializes the 
		super(); 
		this.registerUserRepository = registerUserRepository;
		this.modelMapper = modelMapper;
	}

	
	//Override the registerUser from Registration Service declaration
	@Override
	public String registerUser(UserRegistrationDto userRegistrationRequestDto) throws UserLoginException {
		//Create a User object that is the modelMapper and maps the DTO with the Users.Class
		
		Optional<Users>checkUserName = registerUserRepository.findByUsername(userRegistrationRequestDto.getUsername());
		
		if(checkUserName.isPresent()) {
			throw new UserLoginException(HttpStatus.CONFLICT, ConstantsUtil.USERNAME_ALREADY_EXISTS);
			
		}
		
		Users user = modelMapper.map(userRegistrationRequestDto, Users.class);
		
//		Save the user in the registerUserRepo
		registerUserRepository.save(user);
		return "User has been created.";
	}

}
