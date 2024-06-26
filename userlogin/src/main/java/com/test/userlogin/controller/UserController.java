package com.test.userlogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.userlogin.entity.Users;
import com.test.userlogin.request.LoginDto;
import com.test.userlogin.request.ResetPasswordDto;
import com.test.userlogin.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
		String response = userService.loginUser(loginDto);
		if ("Successfully logged in".equals(response)) {
			return ResponseEntity.ok(response);
		} else if ("User is bloker.".equals(response)
				|| "User bloker."
						.equals(response)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
		String response = userService.resetPassword(resetPasswordDto);
		if ("Password successfully reset".equals(response)) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}
}
