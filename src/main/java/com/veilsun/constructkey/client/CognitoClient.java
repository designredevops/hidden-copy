package com.veilsun.constructkey.client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.veilsun.constructkey.domain.dto.UserRegistration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

@Component
public class CognitoClient {

	@Value("${aws.cognito.userpool.client.id}")
	String userPoolClientId;

	CognitoIdentityProviderClient identityProviderClient;

	@PostConstruct
	private void initCognitoClient() {
		identityProviderClient = CognitoIdentityProviderClient.builder().region(Region.US_EAST_1).build();
	}

	public String signUpUser(UserRegistration registration) {
		List<AttributeType> attributes = new ArrayList<>();
		String userName = "";

		if (registration.getEmail() != null) {
			attributes.add(AttributeType.builder().name("email").value(registration.getEmail()).build());
			userName += registration.getEmail();
		} else if (registration.getMobile() != null) {
			attributes.add(AttributeType.builder().name("mobile").value(registration.getMobile()).build());
			userName += registration.getMobile();
		}

		if (userName == "")
			throw new InternalError("Must provide email or mobile");

		try {
			SignUpRequest signUpRequest = SignUpRequest.builder().userAttributes(attributes).username(userName)
					.clientId(userPoolClientId).password(registration.getPassword()).build();
			SignUpResponse signUpResponse = identityProviderClient.signUp(signUpRequest);

			return signUpResponse.getValueForField("UserSub", String.class).get();

		} catch (Exception e) {
			throw new NoSuchElementException(e.getMessage());
		}
	}

}
