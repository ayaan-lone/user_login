package com.test.userlogin.request;

import javax.validation.constraints.NotEmpty;

public class UserRegistrationDto{
	@NotEmpty(message = "Do not leave emopty,")
	private String username;
	private String email;
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}