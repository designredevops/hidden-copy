package com.veilsun.constructkey.service;

import com.veilsun.constructkey.domain.Organization;
import com.veilsun.constructkey.domain.Team;
import com.veilsun.constructkey.domain.TeamMember;
import com.veilsun.constructkey.domain.User;
import com.veilsun.constructkey.repository.OrganizationRepository;
import com.veilsun.constructkey.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.repository.TeamRepository;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

	public Page<List<User>> findAllUserPool(UUID teamId, Pageable page) {
		// move this to service
		/*Team team = teamRepository.findById(teamId).orElseThrow();
		if(Team.TeamType.OrganizationMember.equals(team.getType())) {
			return Page.empty();
		} else if(Team.TeamType.OrganizationAdmin.equals(team.getType())) {
			// return Organization membersTeam users.
			// Get organization -> then get
			var user = teamMemberRepository.findAllUsersByTeamId(teamId, page).getContent();
			return Page.empty();
		} else if(Team.TeamType.ProjectMember.equals(team.getType())) {
			// return Organization membersTeam users, or Organization adminTeam users
			return teamMemberRepository.findAllUsersByTeamId(teamId, page);
			//return users;
		} else if(Team.TeamType.ProjectAdmin.equals(team.getType())) {
			// return Organization membersTeam users, or Organization adminTeam users, or Project membersTeam
			return teamMemberRepository.findAllUsersByTeamId(teamId, page);
		} else if(Team.TeamType.PullPlanTargetMeeting.equals(team.getType())) {
			// return Project membersTeam user or Project adminTeam user
			return teamMemberRepository.findAllUsersByTeamId(teamId, page);
		}*/
		return teamMemberRepository.findAllUsersByTeamId(teamId, page); //empty list
	}

	public Boolean deleteTeamMember(UUID memberId) {
		teamMemberRepository.deleteById(memberId);
		return true;
	}
}
