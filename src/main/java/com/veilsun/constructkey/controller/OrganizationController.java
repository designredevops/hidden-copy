package com.veilsun.constructkey.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.Organization;
import com.veilsun.constructkey.domain.PullPlanTarget;
import com.veilsun.constructkey.domain.dto.UserOrganizationInvitation;
import com.veilsun.constructkey.service.OrganizationService;
import com.veilsun.constructkey.specification.organization.OrganizationByParentSpec;
import com.veilsun.constructkey.specification.organization.OrganizationIdSpec;
import com.veilsun.constructkey.specification.ppt.PullPlanTargetByOrganizationSpec;

@RestController
@RequestMapping("/org")
public class OrganizationController {

	Logger logger = LoggerFactory.getLogger(OrganizationController.class);

	@Autowired
	OrganizationService organizationService;

	@GetMapping("/{orgId}")
	public ResponseEntity<?> getOrganization(
			@PathVariable("orgId") UUID orgId,
			OrganizationIdSpec spec,
			@RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<Organization>(organizationService.findOne(spec, paths), HttpStatus.OK);
	}

	@GetMapping("/{orgId}/pull-plan-target")
	public ResponseEntity<?> getPPTByOrganization(
			@PathVariable() UUID orgId,
			PullPlanTargetByOrganizationSpec spec,
			Pageable page,
			@RequestParam(name = "paths", required = false) String... paths
	){
		return new ResponseEntity<Page<PullPlanTarget>>(organizationService.getPPTByOrganization(spec, paths, page), HttpStatus.OK);
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
	public ResponseEntity<?> getSubOrganization(
			@PathVariable("orgId") UUID orgId, 
			Pageable page,
			OrganizationByParentSpec spec,
			@RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<Page<Organization>>(organizationService.findAllByParentId(spec, paths, page), HttpStatus.OK);
	}

	@PostMapping("/{orgId}/organization")
	public ResponseEntity<?> createSubOrganization(@ModelAttribute UUID uid, @PathVariable("orgId") UUID parentOrgId, @RequestBody Organization org){
		return new ResponseEntity<Organization>(organizationService.createSubOrganization(uid, parentOrgId, org), HttpStatus.CREATED);
	}
}
