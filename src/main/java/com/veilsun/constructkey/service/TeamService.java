package com.veilsun.constructkey.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.veilsun.constructkey.specification.team.TeamByTeamIdSpec;
import com.veilsun.constructkey.specification.team.TeamMembersByTeamIdSpec;
import com.veilsun.constructkey.utils.EGUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.Team;
import com.veilsun.constructkey.domain.TeamMember;
import com.veilsun.constructkey.domain.User;
import com.veilsun.constructkey.repository.TeamMemberRepository;
import com.veilsun.constructkey.repository.TeamRepository;

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
		} else if(Team.TeamType.ProjectMember.equals(team.getType())) {
			UUID teamIds = teamRepository.getOrganizationTeamsIdByProjectMemberTeamId(teamId).orElseThrow();
			return teamMemberRepository.findAllUsersByTeamIds(Arrays.asList(teamIds), page);
		}  else if(Team.TeamType.PullPlanTargetMeeting.equals(team.getType())) {
			UUID projectTeamIds = teamRepository.getProjectTeamsIdByPPTMeetingTeamId(teamId).orElseThrow();
			return teamMemberRepository.findAllUsersByTeamIds(Arrays.asList(projectTeamIds), page);
		}
		return Page.empty();
	}

	public Boolean deleteTeamMember(UUID memberId) {
		teamMemberRepository.deleteById(memberId);
		return true;
	}

	public Team getTeam(TeamByTeamIdSpec spec, String[] paths) {
		return teamRepository.findOne(spec, EGUtils.fromAttributePaths(paths)).orElseThrow();
	}

	public List<TeamMember> getTeamMembers(TeamMembersByTeamIdSpec spec, String[] paths) {
		return teamMemberRepository.findAll(spec, EGUtils.fromAttributePaths(paths));
	}
}
