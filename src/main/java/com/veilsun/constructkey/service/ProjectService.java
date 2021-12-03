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
		boolean isDeleted = projectRepository.findById(projectId).isEmpty();
		return  isDeleted;
	}

	public Page<ProjectLocation> getProjectLocations(UUID orgId, UUID projectId, Pageable page) {
		return projectLocationRepository.findAllByProjectIdAndProjectOrganizationId(projectId, orgId, page);
	}


	public ProjectLocation createProjectLocation(UUID orgId, UUID projectId, ProjectLocation projectLocation) {
		Optional<Project> projectToAddLocation = projectRepository.findById(projectId);
		projectLocation.setProject(projectToAddLocation.get());
		projectLocationRepository.save(projectLocation);
		return projectLocation;
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
		boolean isDeleted = projectLocationRepository.findById(locationId).isEmpty();
		return isDeleted;
	}

	public Page<ProjectOrganization> getProjectOrganizations(UUID orgId, UUID projectId, Pageable page) {
		return projectOrganizationRepository.findAllByOrganizationIdAndProjectId(orgId, projectId, page);
	}

	public ProjectOrganization createProjectOrganization(
			UUID orgId,
			UUID projectId,
			ProjectOrganization projectOrganization
	) {
		projectOrganization.setProject(projectRepository.findById(projectId).get());
		Optional<Organization> organization =
				Optional.ofNullable(
						projectRepository.findById(projectId).get().getOrganization() == null ?
								new Organization(orgId) : projectRepository.findById(projectId).get().getOrganization());
		projectOrganization.setOrganization(organization.get());
		//projectOrganization.setDisplayStyle();
		//projectOrganization.setWorkSchedule();
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
		 boolean isDeleted = projectLocationRepository.findById(projectOrganizationId).isEmpty();
		 return isDeleted;
	}
}
