package com.veilsun.constructkey.security;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SecurityControllerAdvice {

	@ModelAttribute
	public UUID customPrincipal(Authentication auth) {
		return auth == null ? null : UUID.fromString(auth.getName());
	}
}
