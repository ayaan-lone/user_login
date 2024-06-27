package com.test.userlogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.userlogin.entity.Users;
import com.test.userlogin.exception.UserLoginException;
import com.test.userlogin.request.LoginDto;
import com.test.userlogin.service.UserService;


/* Contains the following Response Entities:
 * 
 * 1. To get all Users
 * 2. To login the user*/
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

// to get all the users list
	@GetMapping
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PostMapping("/login")

	public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) throws UserLoginException {
		String response = userService.loginUser(loginDto);
		return ResponseEntity.ok(response);

	}
	//

}
