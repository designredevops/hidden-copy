package com.veilsun.constructkey.client;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

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
	
	@Value("${aws.cognito.userpool.client.secret}")
	String userPoolClientSecret;
	
	CognitoIdentityProviderClient identityProviderClient;
	
	@PostConstruct
	private void initCognitoClient() {
		identityProviderClient = CognitoIdentityProviderClient.builder()
									.region(Region.US_EAST_1)
									.build();
	}
	
	public String signUpUser(UserRegistration registration) {
		List<AttributeType> attributes = new ArrayList<>();
		String userName = "";
		
		if(registration.getEmail() != null) {
			attributes.add(AttributeType.builder().name("email").value(registration.getEmail()).build());
			userName += registration.getEmail();
		} else if(registration.getMobile() != null) {
			attributes.add(AttributeType.builder().name("mobile").value(registration.getMobile()).build());
			userName += registration.getMobile();
		}
		
		if(userName == "") throw new InternalError("Must provide email or mobile");
		
		try {
			String secretHash = calculateSecretHash(userPoolClientId, userPoolClientSecret, userName);
			SignUpRequest signUpRequest =  SignUpRequest.builder()
					.userAttributes(attributes)
					.username(userName)
					.clientId(userPoolClientId)
					.password(registration.getPassword())
					.secretHash(secretHash)
					.build();
			SignUpResponse signUpResponse = identityProviderClient.signUp(signUpRequest);
			
			return signUpResponse.getValueForField("UserSub", String.class).get();
			
		} catch (Exception e) {
			throw new NoSuchElementException(e.getMessage());
		}
	}
	
	private static String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName) {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            mac.update(userName.getBytes(StandardCharsets.UTF_8));
            byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
            return java.util.Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating user hash");
        }
    }
}
