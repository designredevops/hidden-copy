package com.veilsun.constructkey.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.Project;
import com.veilsun.constructkey.domain.ProjectLocation;
import com.veilsun.constructkey.domain.ProjectOrganization;
import com.veilsun.constructkey.domain.PullPlanTarget;
import com.veilsun.constructkey.service.ProjectService;

@RestController
@RequestMapping("/org/{orgId}/project")
public class ProjectController {

	Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	ProjectService projectService;
	
	@GetMapping("")
	public ResponseEntity<?> getProjectsByOrganization(@RequestParam() String orgId, Pageable page) {
		return new ResponseEntity<Page<Project>>(projectService.getProjectsByOrganization(orgId), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createProject(@RequestParam() String orgId, @RequestBody Project project) {
		return ResponseEntity.ok(new Project());
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProject(@RequestParam() String orgId, @RequestParam() String projectId ) {
		return new ResponseEntity<Project>(projectService.getProjectById(projectId), HttpStatus.OK);
	}
	
	@PutMapping("/{projectId}")
	public ResponseEntity<?> updateProject(
			@RequestParam() String orgId, 
			@RequestParam() String projectId, 
			@RequestBody Project project ) {
		return null;
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@RequestParam() String orgId, @RequestParam() String projectId ) {
		return null;
	}
	
	/*
	 * PROJECT LOCATION
	 */
	
	@GetMapping("/{projectId}/location")
	public ResponseEntity<?> getProjectLocations(@RequestParam() String orgId, @RequestParam() String projectId) {
		return null;
	}
	
	@PostMapping("/{projectId}/location")
	public ResponseEntity<?> createProjectLocation(
			@RequestParam() String orgId, 
			@RequestParam() String projectId, 
			@RequestBody ProjectLocation projectLocation) {
		return null;
	}
	
	@GetMapping("/{projectId}/location/{locationId}")
	public ResponseEntity<?> getProjectLocation(
			@RequestParam() String orgId, 
			@RequestParam() String projectId, 
			@RequestParam() String locationId) {
		return null;
	}
	
	@PutMapping("/{projectId}/location/{locationId}")
	public ResponseEntity<?> updateProjectLocation(
			@RequestParam() String orgId, 
			@RequestParam() String projectId, 
			@RequestParam() String locationId,
			@RequestBody ProjectLocation projectLocation) {
		return null;
	}
	
	@DeleteMapping("/{projectId}/location/{locationId}")
	public ResponseEntity<?> deleteProjectLocation(
			@RequestParam() String orgId, 
			@RequestParam() String projectId, 
			@RequestParam() String locationId) {
		return null;
	}
	
	/**
	 * PROJECT ORGANIZATION
	 */
	
	@GetMapping("/{projectId}/organization")
	public ResponseEntity<?> getProjectOrganizations(@RequestParam() String orgId, @RequestParam() String projectId) {
		return null;
	}
	
	@PostMapping("/{projectId}/organization")
	public ResponseEntity<?> createProjectOrganization(
			@RequestParam() String orgId, 
			@RequestParam() String projectId, 
			@RequestBody ProjectOrganization projectOrganization) {
		return null;
	}
	
	@GetMapping("/{projectId}/organization/{projectOrganizationId}")
	public ResponseEntity<?> getProjectOrganization(
			@RequestParam() String orgId, 
			@RequestParam() String projectId, 
			@RequestParam() String projectOrganizationId) {
		return null;
	}
	
	@PutMapping("/{projectId}/organization/{projectOrganizationId}")
	public ResponseEntity<?> updateProjectOrganization(
			@RequestParam() String orgId, 
			@RequestParam() String projectId, 
			@RequestParam() String projectOrganizationId,
			@RequestBody ProjectOrganization projectOrganization) {
		return null;
	}
	
	@DeleteMapping("/{projectId}/organization/{projectOrganizationId}")
	public ResponseEntity<?> deleteProjectOrganization(
			@RequestParam() String orgId, 
			@RequestParam() String projectId, 
			@RequestParam() String projectOrganizationId) {
		return null;
	}
	
	
}