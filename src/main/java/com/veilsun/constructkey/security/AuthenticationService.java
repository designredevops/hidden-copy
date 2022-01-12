package com.veilsun.constructkey.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.repository.OrganizationRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	private OrganizationRepository organizationRepository;

	public UUID userId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return UUID.fromString(authentication.getName());
	}
	
	public boolean isOrganizationAdmin(UUID orgId) {
		Integer r = organizationRepository.isOrganizationAdmin(orgId, userId());
		return r == null;
	}
}
