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
		
		User createdUser = userRepository.findOneByEmailOrMobile(registration.getEmail(), registration.getMobile()).get();
		if(createdUser != null) {
			if(createdUser.getEmail() == null && registration.getEmail() != null) {
				createdUser.setEmail(registration.getEmail());
				//TODO: Update cognito email
			}
			if(createdUser.getMobile() == null && registration.getMobile() != null) {
				createdUser.setMobile(registration.getMobile());
				//TODO: Update cognito mobile
			}
			
			return userRepository.save(createdUser);
		} else {			
			String cognitoUUID = cognito.signUpUser(registration);
			
			User u = new User();
			u.setId(UUID.fromString(cognitoUUID));
			u.setEmail(registration.getEmail());
			u.setMobile(registration.getMobile());
			
			createdUser = userRepository.save(u);
		}
		
		
		return createdUser;
	}

}
