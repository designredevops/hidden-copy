package com.veilsun.constructkey.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.Organization;
import com.veilsun.constructkey.domain.dto.UserOrganizationInvitation;
import com.veilsun.constructkey.service.OrganizationService;

@RestController
@RequestMapping("/org/{orgId}")
public class OrganizationController {

	Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	OrganizationService organizationService;
	
	@GetMapping("")
	public ResponseEntity<?> getOrganization(@RequestParam("orgId") String orgId) {
		return new ResponseEntity<Organization>(organizationService.getOrganizationById(orgId), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createOrganization(Principal principal, Organization org) {
		return new ResponseEntity<Organization>(organizationService.createOrganization(principal.getName(), org), HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateOrganization(@RequestParam("orgId") String orgId, Organization org) {
		return new ResponseEntity<Organization>(organizationService.updateOrganization(orgId, org), HttpStatus.OK);
	}
	
	@PostMapping("/invite")
	public ResponseEntity<?> inviteToOrganization(@RequestParam("orgId") String orgId, @RequestBody UserOrganizationInvitation invitation) {
		return new ResponseEntity<Organization>(organizationService.inviteUser(invitation), HttpStatus.OK);
	}
	
	@GetMapping("/organization")
	public ResponseEntity<?> getSubOrganization(@RequestParam("orgId") String orgId) {
		return new ResponseEntity<Page<Organization>>(organizationService.getOrganizationByParentId(orgId), HttpStatus.OK);
	}
	
	
}
