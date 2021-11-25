package com.veilsun.constructkey.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.Organization;
import com.veilsun.constructkey.domain.User;
import com.veilsun.constructkey.domain.UserInvitation;
import com.veilsun.constructkey.domain.UserInvitation.InvitationStatus;
import com.veilsun.constructkey.repository.OrganizationRepository;
import com.veilsun.constructkey.repository.UserInvitationRepository;
import com.veilsun.constructkey.repository.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private UserInvitationRepository userInvitationRepository;
	
	public User getProfileById(UUID userId) {
		return userRepository.getUserById(userId).orElseThrow();
	}
	
	public Page<Organization> getOrganizationsByUserId(UUID userId, Pageable page) {
		return organizationRepository.findAllByMemberTeamMembersUserId(userId, page);
	}

	public Page<UserInvitation> getNewInvitationsByUserId(String userId, Pageable page) {
		return userInvitationRepository.getInvitationsByUserIdAndStatus(userId, InvitationStatus.NEW, page);
	}

	public User updateProfile(UUID userId, User user) {
		User existingUser = userRepository.getUserById(userId).orElseThrow();
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		
		// if email is being changed, an email confirmation must be sent to the original email if the email has been verified
		// that confirmation contains a link to perform the actual email change
		
		
		userRepository.save(existingUser);
		
		return existingUser;
	}

}
