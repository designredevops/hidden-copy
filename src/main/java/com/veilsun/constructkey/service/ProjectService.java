package com.veilsun.constructkey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.Project;
import com.veilsun.constructkey.domain.Team;
import com.veilsun.constructkey.domain.Team.TeamType;
import com.veilsun.constructkey.domain.dto.UserProjectInvitation;
import com.veilsun.constructkey.repository.ProjectRepository;

@Service
public class ProjectService {


	@Autowired
	private ProjectRepository projectRepository;

	public Project createProject(String userId, Project project) {
		project.setAdminTeam(new Team(userId, TeamType.ProjectAdmin));
		project.setMemberTeam(new Team(TeamType.ProjectMember));
		Project createdProject = projectRepository.save(project);
		return createdProject;
	}

	public Project getProjectById(String projectId) {
		return projectRepository.findById(projectId).orElseThrow();
	}

	public Project updateProject(String projectId, Project project) {
		return projectRepository.save(project);
	}

	public Project inviteUser(UserProjectInvitation invitation) {
		//TODO: Check if the user is already added
		
		//TODO: Add user to project members
		return null;
	}

	public Page<Project> getProjectsByOrganization(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
