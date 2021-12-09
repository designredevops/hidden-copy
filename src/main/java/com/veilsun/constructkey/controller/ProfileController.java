package com.veilsun.constructkey.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.Organization;
import com.veilsun.constructkey.domain.User;
import com.veilsun.constructkey.domain.UserInvitation;
import com.veilsun.constructkey.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("")
	public ResponseEntity<?> getProfile(@ModelAttribute UUID uid) {
		return new ResponseEntity<User>(profileService.getProfileById(uid), HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateProfile(@ModelAttribute UUID uid, @RequestBody User user) {
		return new ResponseEntity<User>(profileService.updateProfile(uid, user), HttpStatus.OK);
	}
	
	@GetMapping("/org")
	public ResponseEntity<?> getOrganizations(@ModelAttribute UUID uid, Pageable page) {
		return new ResponseEntity<Page<Organization>>(profileService.getOrganizationsByUserId(uid, page), HttpStatus.OK);
	}
	
	@GetMapping("/invitation")
	public ResponseEntity<?> getNewInvitations(@ModelAttribute UUID uid, Pageable page) {
		return new ResponseEntity<Page<UserInvitation>>(profileService.getNewInvitationsByUserId(uid, page), HttpStatus.OK);
	}
}
