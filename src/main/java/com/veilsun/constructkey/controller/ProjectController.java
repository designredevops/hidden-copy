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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.Project;
import com.veilsun.constructkey.domain.ProjectLocation;
import com.veilsun.constructkey.domain.ProjectOrganization;
import com.veilsun.constructkey.service.ProjectService;

@RestController
@RequestMapping("/org/{orgId}/project")
public class ProjectController {

	Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	ProjectService projectService;
	
	// GET https://constructkey.com/api/org/2882-al-28340-alkjd932/project
	@GetMapping("")
	public ResponseEntity<?> getProjectsByOrganization(@PathVariable() String orgId, Pageable page) {
		return new ResponseEntity<Page<Project>>(projectService.getProjectsByOrganization(orgId, page), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createProject(@PathVariable() String orgId, @RequestBody Project project) {
		return ResponseEntity.ok(new Project());
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProject(@PathVariable() String orgId, @PathVariable() String projectId ) {
		return new ResponseEntity<Project>(projectService.getProjectById(projectId), HttpStatus.OK);
	}
	
	@PutMapping("/{projectId}")
	public ResponseEntity<?> updateProject(
			@PathVariable() String orgId, 
			@PathVariable() String projectId, 
			@RequestBody Project project ) {
		return null;
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable() String orgId, @PathVariable() String projectId ) {
		return null;
	}
	
	/*
	 * PROJECT LOCATION
	 */
	
	@GetMapping("/{projectId}/location")
	public ResponseEntity<?> getProjectLocations(@PathVariable() String orgId, @PathVariable() String projectId) {
		return null;
	}
	
	@PostMapping("/{projectId}/location")
	public ResponseEntity<?> createProjectLocation(
			@PathVariable() String orgId, 
			@PathVariable() String projectId, 
			@RequestBody ProjectLocation projectLocation) {
		return null;
	}
	
	@GetMapping("/{projectId}/location/{locationId}")
	public ResponseEntity<?> getProjectLocation(
			@PathVariable() String orgId, 
			@PathVariable() String projectId, 
			@PathVariable() String locationId) {
		return null;
	}
	
	@PutMapping("/{projectId}/location/{locationId}")
	public ResponseEntity<?> updateProjectLocation(
			@PathVariable() String orgId, 
			@PathVariable() String projectId, 
			@PathVariable() String locationId,
			@RequestBody ProjectLocation projectLocation) {
		return null;
	}
	
	@DeleteMapping("/{projectId}/location/{locationId}")
	public ResponseEntity<?> deleteProjectLocation(
			@PathVariable() String orgId, 
			@PathVariable() String projectId, 
			@PathVariable() String locationId) {
		return null;
	}
	
	/**
	 * PROJECT ORGANIZATION
	 */
	
	@GetMapping("/{projectId}/organization")
	public ResponseEntity<?> getProjectOrganizations(@PathVariable() String orgId, @PathVariable() String projectId) {
		return null;
	}
	
	@PostMapping("/{projectId}/organization")
	public ResponseEntity<?> createProjectOrganization(
			@PathVariable() String orgId, 
			@PathVariable() String projectId, 
			@RequestBody ProjectOrganization projectOrganization) {
		return null;
	}
	
	@GetMapping("/{projectId}/organization/{projectOrganizationId}")
	public ResponseEntity<?> getProjectOrganization(
			@PathVariable() String orgId, 
			@PathVariable() String projectId, 
			@PathVariable() String projectOrganizationId) {
		return null;
	}
	
	@PutMapping("/{projectId}/organization/{projectOrganizationId}")
	public ResponseEntity<?> updateProjectOrganization(
			@PathVariable() String orgId, 
			@PathVariable() String projectId, 
			@PathVariable() String projectOrganizationId,
			@RequestBody ProjectOrganization projectOrganization) {
		return null;
	}
	
	@DeleteMapping("/{projectId}/organization/{projectOrganizationId}")
	public ResponseEntity<?> deleteProjectOrganization(
			@PathVariable() String orgId, 
			@PathVariable() String projectId, 
			@PathVariable() String projectOrganizationId) {
		return null;
	}
	
	
}