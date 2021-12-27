package com.veilsun.constructkey.controller;

import java.security.Principal;
import java.util.UUID;

import com.veilsun.constructkey.domain.PullPlanTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("")
	public ResponseEntity<?> getPPTByOrganization(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			Pageable page
	){
		return new ResponseEntity<Page<PullPlanTarget>>(organizationService.getPPTByOrganization(orgId, page), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<?> createOrganization(@ModelAttribute UUID uid, @RequestBody Organization org) {
		return new ResponseEntity<Organization>(organizationService.createOrganization(uid, org), HttpStatus.CREATED);
	}
	
	@PutMapping("/{orgId}")
	public ResponseEntity<?> updateOrganization(@PathVariable("orgId") UUID orgId, Organization org) {
		return new ResponseEntity<Organization>(organizationService.updateOrganization(orgId, org), HttpStatus.OK);
	}
	
	@DeleteMapping("/{orgId}")
	public ResponseEntity<?> deleteOrganization(@PathVariable("orgId") UUID orgId) {
		return new ResponseEntity<Boolean>(organizationService.deleteOrganization(orgId), HttpStatus.OK);
	}
	
	@PostMapping("/{orgId}/invite")
	public ResponseEntity<?> inviteToOrganization(@PathVariable("orgId") UUID orgId, @RequestBody UserOrganizationInvitation invitation) {
		return new ResponseEntity<Organization>(organizationService.inviteUser(invitation), HttpStatus.OK);
	}
	
	@GetMapping("/{orgId}/organization")
	public ResponseEntity<?> getSubOrganization(@PathVariable("orgId") UUID orgId, Pageable page) {
		return new ResponseEntity<Page<Organization>>(organizationService.getOrganizationByParentId(orgId, page), HttpStatus.OK);
	}

	@GetMapping("/{orgId}/pull-plan-target")
	public ResponseEntity<?> getPPTByOrganization(
			@PathVariable() UUID orgId,
			Pageable page
	){
		return new ResponseEntity<Page<PullPlanTarget>>(organizationService.getPPTByOrganization(orgId, page), HttpStatus.OK);
	}
}
