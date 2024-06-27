package com.test.userlogin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

// A Table named users is created, all the fields are present here.
@Entity
@Table(name = "users")

public class Users implements Serializable {

	private static final long serialVersionUID = 8500851767043648592L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	// Should be Unique
	@Column(unique = true)
	@NotNull
	private String username;

	@Column
	private String password;

	// Should be Unique
	@Column
	@NotNull
	private String email;

	// Is the User Blocked or not(By Default it is set to False)
	@Column
	private boolean blocked = false;

	// Time to check 24 hours
	@Column
	private LocalDateTime blockTime;

	// A counter for User Login Attempts.
	@Column
	private int loginAttempts = 0;

	private String Otp;
	private boolean isVerifiedOtp = false;
	private LocalDateTime optGenerationTime;

	public LocalDateTime getOptGenerationTime() {
		return optGenerationTime;
	}

	public void setOptGenerationTime(LocalDateTime optGenerationTime) {
		this.optGenerationTime = optGenerationTime;
	}

	public String getOtp() {
		return Otp;
	}

	public void setOtp(String otp) {
		Otp = otp;
	}

	public boolean setVerifiedOtp() {
		return isVerifiedOtp;
	}

	public void setVerifiedOtp(boolean isVerifiedOtp) {
		this.isVerifiedOtp = isVerifiedOtp;
	}

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public LocalDateTime getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(LocalDateTime blockTime) {
		this.blockTime = blockTime;
	}

	// Getters and Setters are here

}
