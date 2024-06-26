package com.test.userlogin.service;

import com.test.userlogin.entity.Users;
import com.test.userlogin.request.LoginDto;
import com.test.userlogin.request.ResetPasswordDto;

import java.util.List;

public interface UserService {
	List<Users> getAllUsers();

	String loginUser(LoginDto loginDto);

	String resetPassword(ResetPasswordDto resetPasswordDto);

}
