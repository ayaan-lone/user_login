package com.test.userlogin.entity;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class Users implements Serializable {

	private static final long serialVersionUID = 8500851767043648592L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String email;
	

	
	@Column
	private boolean blocked = false;
	
	@Column
	private LocalDateTime blockTime;
	
	@Column
	private int loginAttempts = 0;
	
	

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
	
	
	
	


	

}
