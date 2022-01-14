package com.veilsun.constructkey.client;

import java.io.StringReader;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.annotation.PostConstruct;

import org.bouncycastle.util.io.pem.PemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JWTClient {
	
	@Value("${app.key.public}")
	private String publicKeyString;
	
	@Value("${app.key.private}")
	private String privateKeyString;
	
	
	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;
	
	Logger logger = LoggerFactory.getLogger(JWTClient.class);

	
	@PostConstruct
	private void loadKeys() {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			
			byte[] publicKeyBytes = new PemReader(new StringReader(publicKeyString)).readPemObject().getContent();
			publicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
			
			byte[] privateKeyBytes = new PemReader(new StringReader(privateKeyString)).readPemObject().getContent();
			privateKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	public String generateToken(String payload) throws Exception {
		String token = null;
		try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            token = JWT.create()
            		.withClaim("payload", payload)
                    .sign(algorithm);
        } catch (Exception x) {
            throw x;
        }
		
		return token;
	}
	
	public String getPublicKey() {
		return publicKeyString;
	}
}
