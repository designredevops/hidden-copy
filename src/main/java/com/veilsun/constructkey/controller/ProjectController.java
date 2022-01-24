package com.veilsun.constructkey.controller;

import com.veilsun.constructkey.specification.project.ProjectByOrganizationSpec;
import com.veilsun.constructkey.specification.project.ProjectLocationsSpec;
import com.veilsun.constructkey.specification.project.ProjectOrganizationSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veilsun.constructkey.domain.Project;
import com.veilsun.constructkey.domain.ProjectLocation;
import com.veilsun.constructkey.domain.ProjectOrganization;
import com.veilsun.constructkey.service.ProjectService;
import com.veilsun.constructkey.specification.project.ProjectIdSpec;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/org/{orgId}/project")
public class ProjectController {

	Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	ProjectService projectService;
	
	// GET https://constructkey.com/api/org/2882-al-28340-alkjd932/project
	@GetMapping("")
	public ResponseEntity<?> getProjectsByOrganization(
			@PathVariable() UUID orgId,
			Pageable page,
			ProjectByOrganizationSpec spec,
			@RequestParam(name = "paths", required = false) String... paths
			) {
		return new ResponseEntity<Page<Project>>(projectService.getProjectsByOrganization(spec, page, paths), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createProject(@ModelAttribute UUID uid, @PathVariable() UUID orgId, @Valid @RequestBody Project project) {
		return new ResponseEntity<Project>(projectService.createProject(uid, project, orgId), HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProject(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			ProjectIdSpec spec,
			@RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<Project>(projectService.getProject(spec, paths), HttpStatus.OK);
	}
	
	@PutMapping("/{projectId}")
	public ResponseEntity<?> updateProject(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId, 
			@Valid @RequestBody Project project ) {
		return new ResponseEntity<Project>(projectService.updateProject(projectId, project), HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable() UUID orgId, @PathVariable() UUID projectId ) {

		return new ResponseEntity<Boolean>(projectService.deleteProject(orgId, projectId), HttpStatus.OK);
	}
	
	/*
	 * PROJECT LOCATION
	 */
	
	@GetMapping("/{projectId}/location")
	public ResponseEntity<?> getProjectLocations(@PathVariable() UUID orgId,
												 @PathVariable() UUID projectId,
												 Pageable page,
												 ProjectLocationsSpec spec,
												 @RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<Page<ProjectLocation>>(projectService.getProjectLocations(
				spec, page, paths), HttpStatus.OK);
	}
	
	@PostMapping("/{projectId}/location")
	public ResponseEntity<?> createProjectLocation(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@Valid @RequestBody ProjectLocation projectLocation) {
		return new ResponseEntity<ProjectLocation>(projectService.createProjectLocation(orgId, projectId, projectLocation), HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}/location/{locationId}")
	public ResponseEntity<?> getProjectLocation(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId, 
			@PathVariable() UUID locationId,
			ProjectLocationsSpec spec,
			@RequestParam(name = "paths", required = false) String... paths
	) {
		return new ResponseEntity<ProjectLocation>(projectService.getProjectLocation(spec, paths), HttpStatus.OK);
	}
	
	@PutMapping("/{projectId}/location/{locationId}")
	public ResponseEntity<?> updateProjectLocation(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId, 
			@PathVariable() UUID locationId,
			@RequestBody ProjectLocation projectLocation) {
		return new ResponseEntity<ProjectLocation>(projectService.updateProjectLocation(orgId, projectId, locationId, projectLocation), HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectId}/location/{locationId}")
	public ResponseEntity<?> deleteProjectLocation(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId, 
			@PathVariable() UUID locationId) {
		return new ResponseEntity<Boolean>(projectService.deleteProjectLocation(locationId), HttpStatus.OK);
	}
	
	/**
	 * PROJECT ORGANIZATION
	 */
	
	@GetMapping("/{projectId}/organization")
	public ResponseEntity<?> getProjectOrganizations(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			Pageable page,
			ProjectOrganizationSpec spec,
			@RequestParam(name = "paths", required = false) String... paths
	) {
		return new ResponseEntity<Page<ProjectOrganization>>(
				projectService.getProjectOrganizations(spec, page, paths),
				HttpStatus.OK
		);
	}
	
	@PostMapping("/{projectId}/organization")
	public ResponseEntity<?> createProjectOrganization(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId, 
			@RequestBody ProjectOrganization projectOrganization) {
		return new ResponseEntity<ProjectOrganization>(projectService.createProjectOrganization(orgId, projectId, projectOrganization), HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}/organization/{projectOrganizationId}")
	public ResponseEntity<?> getProjectOrganization(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId, 
			@PathVariable() UUID projectOrganizationId,
			ProjectOrganizationSpec spec,
			@RequestParam(name = "paths", required = false) String... paths
			) {
		return new ResponseEntity<ProjectOrganization>(projectService.getProjectOrganization(spec, paths), HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectId}/organization/{projectOrganizationId}")
	public ResponseEntity<?> deleteProjectOrganization(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId, 
			@PathVariable() UUID projectOrganizationId) {
		return new ResponseEntity<Boolean>(projectService.deleteProjectOrganization(projectOrganizationId), HttpStatus.OK);
	}
	
	
}