package com.veilsun.constructkey.service;

import com.veilsun.constructkey.domain.Team;
import com.veilsun.constructkey.domain.TeamMember;
import com.veilsun.constructkey.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.repository.TeamRepository;

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

}
