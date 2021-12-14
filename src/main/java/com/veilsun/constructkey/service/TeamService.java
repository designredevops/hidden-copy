package com.veilsun.constructkey.service;

import com.veilsun.constructkey.domain.Organization;
import com.veilsun.constructkey.domain.Team;
import com.veilsun.constructkey.domain.TeamMember;
import com.veilsun.constructkey.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.repository.TeamRepository;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.UUID;

@Service
public class TeamService {


	@Autowired
	private TeamRepository teamRepository;

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
}
