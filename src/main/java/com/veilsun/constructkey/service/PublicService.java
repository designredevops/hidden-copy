package com.veilsun.constructkey.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.client.CognitoClient;
import com.veilsun.constructkey.domain.User;
import com.veilsun.constructkey.domain.dto.UserRegistration;
import com.veilsun.constructkey.repository.UserRepository;

@Service
public class PublicService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CognitoClient cognito;
	
	public User registerUser(UserRegistration registration) {
		
		String cognitoUUID = cognito.signUpUser(registration);
		
		User u = new User();
		u.setId(UUID.fromString(cognitoUUID));
		u.setEmail(registration.getEmail());
		u.setMobile(registration.getMobile());
		
		User createdUser = userRepository.save(u);
		
		
		return createdUser;
	}

}
