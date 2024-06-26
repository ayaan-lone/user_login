package com.test.userlogin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class HealthCheckController {

	@GetMapping("health-check")

	// TODO Auto-generated constructor stub

	public ResponseEntity<String> healthCheck() {

		return ResponseEntity.status(HttpStatus.OK).body("Health Check : Fine");
	}

}
