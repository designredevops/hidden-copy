package com.veilsun.constructkey.controller;

import java.security.Principal;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.Organization;
import com.veilsun.constructkey.domain.dto.UserOrganizationInvitation;
import com.veilsun.constructkey.service.OrganizationService;

@RestController
@RequestMapping("/org")
public class OrganizationController {

	Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	OrganizationService organizationService;
	
	@GetMapping("/{orgId}")
	public ResponseEntity<?> getOrganization(@PathVariable("orgId") UUID orgId) {
		return new ResponseEntity<Organization>(organizationService.getOrganizationById(orgId), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createOrganization(Principal principal, @RequestBody Organization org) {
		return new ResponseEntity<Organization>(organizationService.createOrganization(principal.getName(), org), HttpStatus.CREATED);
	}
	
	@PutMapping("/{orgId}")
	public ResponseEntity<?> updateOrganization(@PathVariable("orgId") String orgId, Organization org) {
		return new ResponseEntity<Organization>(organizationService.updateOrganization(orgId, org), HttpStatus.OK);
	}
	
	@DeleteMapping("/{orgId}")
	public ResponseEntity<?> deleteOrganization(@PathVariable("orgId") String orgId) {
		return new ResponseEntity<Boolean>(organizationService.deleteOrganization(orgId), HttpStatus.OK);
	}
	
	@PostMapping("/{orgId}/invite")
	public ResponseEntity<?> inviteToOrganization(@PathVariable("orgId") String orgId, @RequestBody UserOrganizationInvitation invitation) {
		return new ResponseEntity<Organization>(organizationService.inviteUser(invitation), HttpStatus.OK);
	}
	
	@GetMapping("/{orgId}/organization")
	public ResponseEntity<?> getSubOrganization(@PathVariable("orgId") String orgId, Pageable page) {
		return new ResponseEntity<Page<Organization>>(organizationService.getOrganizationByParentId(orgId, page), HttpStatus.OK);
	}
	
	
}
