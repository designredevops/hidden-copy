package com.veilsun.constructkey.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.Team;
import com.veilsun.constructkey.domain.TeamMember;
import com.veilsun.constructkey.domain.User;
import com.veilsun.constructkey.repository.TeamMemberRepository;
import com.veilsun.constructkey.repository.TeamRepository;
import com.veilsun.constructkey.repository.TeamRepository.TeamIds;

@Service
public class TeamService {


	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private TeamMemberRepository teamMemberRepository;

	public Team getTeam(UUID teamId) {
		return teamRepository.findById(teamId).orElseThrow();
	}

	public List<TeamMember> getTeamMembers(UUID teamId) {
		return teamMemberRepository.findAllByTeamId(teamId);
	}

	public TeamMember updateTeamMember(UUID teamId, UUID memberId, TeamMember member) {
		TeamMember originalTeamMember = teamMemberRepository.findOneByTeamIdAndId(teamId, memberId);
		if (member.getRole() != null) originalTeamMember.setRole(member.getRole());
		if (member.getStatus() != null) originalTeamMember.setStatus(member.getStatus());
		if (member.getType() != null) originalTeamMember.setType(member.getType());

		return teamMemberRepository.save(originalTeamMember);
	}

	public TeamMember addTeamMember(UUID teamId, TeamMember member) {
		TeamMember teamMemberToAdd = member;
		Team teamToIntegrate = new Team(teamId);
		teamMemberToAdd.setTeam(teamToIntegrate);
		return teamMemberRepository.save(teamMemberToAdd);
	}

	public Page<User> findAllUserPool(UUID teamId, Pageable page) {
		Team team = teamRepository.findById(teamId).orElseThrow();
		if(Team.TeamType.OrganizationMember.equals(team.getType())) {
			return Page.empty();
		} else if(Team.TeamType.OrganizationAdmin.equals(team.getType())) {
			// return organization membersTeam users
			return teamMemberRepository.findAllUsersByTeamId(teamRepository.getOrganizationMembersTeamIdByOrganzationAdminTeamId(teamId).orElseThrow(), page);
		} else if(Team.TeamType.ProjectMember.equals(team.getType())) {
			// return Organization membersTeam users, and Organization adminTeam users
			TeamIds teamIds = teamRepository.getOrganizationTeamsIdByProjectMemberTeamId(teamId).orElseThrow();
			return teamMemberRepository.findAllUsersByTeamIds(Arrays.asList(teamIds.getAdminTeamId(), teamIds.getMemberTeamId()), page);
			//return users;
		} else if(Team.TeamType.ProjectAdmin.equals(team.getType())) {
			// return Organization membersTeam users, or Organization adminTeam users, or Project membersTeam
			TeamIds orgTeamIds = teamRepository.getOrganizationTeamsIdByProjectAdminTeamId(teamId).orElseThrow();
			UUID projectMemberTeamId = teamRepository.getProjectMembersTeamIdByProjectAdminTeamId(teamId).orElseThrow();
			return teamMemberRepository.findAllUsersByTeamIds(Arrays.asList(orgTeamIds.getAdminTeamId(), orgTeamIds.getMemberTeamId(), projectMemberTeamId), page);
		} else if(Team.TeamType.PullPlanTargetMeeting.equals(team.getType())) {
			// return Project membersTeam user or Project adminTeam user
			TeamIds projectTeamIds = teamRepository.getProjectTeamsIdByPPTMeetingTeamId(teamId).orElseThrow();
			return teamMemberRepository.findAllUsersByTeamIds(Arrays.asList(projectTeamIds.getAdminTeamId(), projectTeamIds.getMemberTeamId()), page);
		}
		return Page.empty();
	}

	public Boolean deleteTeamMember(UUID memberId) {
		teamMemberRepository.deleteById(memberId);
		return true;
	}
}
