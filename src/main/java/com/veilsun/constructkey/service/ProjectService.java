package com.veilsun.constructkey.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.DisplayStyle;
import com.veilsun.constructkey.domain.Organization;
import com.veilsun.constructkey.domain.Project;
import com.veilsun.constructkey.domain.ProjectLocation;
import com.veilsun.constructkey.domain.ProjectOrganization;
import com.veilsun.constructkey.domain.Team;
import com.veilsun.constructkey.domain.Team.TeamType;
import com.veilsun.constructkey.domain.TeamMember.TeamMemberRole;
import com.veilsun.constructkey.domain.WorkSchedule;
import com.veilsun.constructkey.domain.dto.UserProjectInvitation;
import com.veilsun.constructkey.repository.ProjectLocationRepository;
import com.veilsun.constructkey.repository.ProjectOrganizationRepository;
import com.veilsun.constructkey.repository.ProjectRepository;
import com.veilsun.constructkey.utils.EGUtils;

@Service
public class ProjectService {
	@Autowired
	private ProjectLocationRepository projectLocationRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectOrganizationRepository projectOrganizationRepository;

	public Project createProject(UUID userId, Project project, UUID orgId) {
		project.setMemberTeam(new Team(userId, TeamType.ProjectMember, TeamMemberRole.Admin));
		project.setOrganization(new Organization(orgId));
		project.setWorkSchedule(new WorkSchedule());
		Project createdProject = projectRepository.save(project);
		return createdProject;
	}

	public Project getProjectById(UUID projectId) {
		return projectRepository.findById(projectId).orElseThrow();
	}
	
	public Project getProject(Specification<Project> spec, String... paths) {
		return projectRepository.findOne(spec, EGUtils.fromAttributePaths(paths)).orElseThrow();
	}

	public Project updateProject(UUID projectId, Project project) {
		Project originalProject = projectRepository.findById(projectId).orElseThrow();
		if (project.getArchived() != null) originalProject.setArchived(project.getArchived());
		if (project.getDescription() != null) originalProject.setDescription(project.getDescription());
		if (project.getEndDate() != null) originalProject.setEndDate(project.getEndDate());
		if (project.getName() != null) originalProject.setName(project.getName());
		if (project.getNumber() != null) originalProject.setNumber(project.getNumber());
		if (project.getStartDate() != null) originalProject.setStartDate(project.getStartDate());
		if (project.getAddress() != null) originalProject.setAddress(project.getAddress());

		return projectRepository.save(originalProject);
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
		ProjectLocation originalProjectLocation = projectLocationRepository.findById(locationId).orElseThrow();
		if (projectLocation.getName() != null) originalProjectLocation.setName(projectLocation.getName());
		return projectLocationRepository.save(originalProjectLocation);
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

	public ProjectOrganization getProjectOrganization(UUID projectOrganizationId) {
		ProjectOrganization projectOrganization = projectOrganizationRepository.findById(projectOrganizationId).orElseThrow();
		return projectOrganization;
	}

	public Boolean deleteProjectOrganization(UUID projectOrganizationId) {
		 projectOrganizationRepository.deleteById(projectOrganizationId);
		 return true;
	}
}
