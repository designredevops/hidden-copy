package com.veilsun.constructkey.service;

import com.veilsun.constructkey.domain.*;
import com.veilsun.constructkey.repository.ProjectLocationRepository;
import com.veilsun.constructkey.repository.ProjectOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.Team.TeamType;
import com.veilsun.constructkey.domain.dto.UserProjectInvitation;
import com.veilsun.constructkey.repository.ProjectRepository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {
	@Autowired
	private ProjectLocationRepository projectLocationRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectOrganizationRepository projectOrganizationRepository;

	public Project createProject(UUID userId, Project project, UUID orgId) {
		project.setAdminTeam(new Team(userId, TeamType.ProjectAdmin));
		project.setMemberTeam(new Team(TeamType.ProjectMember));
		project.setOrganization(new Organization(orgId));
		Project createdProject = projectRepository.save(project);
		return createdProject;
	}

	public Project getProjectById(UUID projectId) {
		return projectRepository.findById(projectId).orElseThrow();
	}

	public Project updateProject(UUID projectId, Project project) {
		return projectRepository.save(project);
	}

	public Project inviteUser(UserProjectInvitation invitation) {
		//TODO: Check if the user is already added
		
		//TODO: Add user to project members
		return null;
	}

	public Page<Project> getProjectsByOrganization(UUID orgId, Pageable page) {
		// TODO Auto-generated method stub
		// JPA
		return projectRepository.findAllByOrganizationId(orgId, page);
	}

	public Boolean deleteProject(UUID orgId, UUID projectId) {
		projectRepository.deleteById(projectId);
		return true;
	}

	public Page<ProjectLocation> getProjectLocations(UUID orgId, UUID projectId, Pageable page) {
		return projectLocationRepository.findAllByProjectIdAndProjectOrganizationId(projectId, orgId, page);
	}


	public ProjectLocation createProjectLocation(UUID orgId, UUID projectId, ProjectLocation projectLocation) {
		projectLocation.setProject(new Project(projectId));
		return projectLocationRepository.save(projectLocation);
	}

	public ProjectLocation getProjectLocation(UUID orgId, UUID projectId, UUID locationId) {
		ProjectLocation location = projectLocationRepository.findByProjectIdAndId(projectId, locationId);
		return location;
	}

	public ProjectLocation updateProjectLocation(UUID orgId, UUID projectId, UUID locationId, ProjectLocation projectLocation) {
		return projectLocationRepository.save(projectLocation);
	}

	public Boolean deleteProjectLocation(UUID locationId) {
		projectLocationRepository.deleteById(locationId);
		return true;
	}

	public Page<ProjectOrganization> getProjectOrganizations(UUID orgId, UUID projectId, Pageable page) {
		return projectOrganizationRepository.findAllByOrganizationIdAndProjectId(orgId, projectId, page);
	}

	public ProjectOrganization createProjectOrganization(
			UUID orgId,
			UUID projectId,
			ProjectOrganization projectOrganization
	) {
		projectOrganization.setProject(new Project(projectId));
		projectOrganization.setOrganization(new Organization(orgId));
		projectOrganization.setDisplayStyle(new DisplayStyle());
		projectOrganization.setWorkSchedule(new WorkSchedule());
		projectOrganization.setUpdatedOn(LocalDateTime.now());
		projectOrganization.setCreatedOn(LocalDateTime.now());
		ProjectOrganization createdProject = projectOrganizationRepository.save(projectOrganization);
		return createdProject;
	}

	public ProjectOrganization updateProjectOrganization(ProjectOrganization projectOrganization) {
		return projectOrganizationRepository.save(projectOrganization);
	}

	public ProjectOrganization getProjectOrganization(UUID projectOrganizationId) {
		ProjectOrganization projectOrganization = projectOrganizationRepository.findById(projectOrganizationId).orElseThrow();
		return projectOrganization;
	}

	public Boolean deleteProjectOrganization(UUID projectOrganizationId) {
		 projectOrganizationRepository.deleteById(projectOrganizationId);
		 return true;
	}
}
