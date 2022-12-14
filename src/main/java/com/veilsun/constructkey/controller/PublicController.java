package com.veilsun.constructkey.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.client.JWTClient;
import com.veilsun.constructkey.domain.User;
import com.veilsun.constructkey.domain.dto.UserRegistration;
import com.veilsun.constructkey.service.PublicService;

@RestController
@RequestMapping("/public")
public class PublicController {

	Logger logger = LoggerFactory.getLogger(PublicController.class);
	
	@Autowired
	private JWTClient jwtClient;
	
	@Autowired
	private PublicService publicService;
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody UserRegistration registration) {
		return new ResponseEntity<User>(publicService.registerUser(registration), HttpStatus.CREATED);
	}
	
	@GetMapping("/health")
	public ResponseEntity<String> health() {
		return new ResponseEntity<String>("Ok", HttpStatus.OK);
	}
	
	@GetMapping("/key")
	public ResponseEntity<String> getPublicKey() {
		return new ResponseEntity<String>(jwtClient.getPublicKey(), HttpStatus.OK);
	}
}
