package com.test.userlogin.service;

import java.util.List;

import com.test.userlogin.entity.Users;
import com.test.userlogin.exception.UserLoginException;
import com.test.userlogin.request.LoginDto;

public interface UserService {

	// Created a List type Abstract Method that will return all the user Objects
	// from the database
	List<Users> getAllUsers();

	// Created a String type Abstract Method that will log in and return a string
	// message
	String loginUser(LoginDto loginDto) throws UserLoginException;

	// Same as Above, will return a string message

}
