package com.veilsun.constructkey.controller;

import com.veilsun.constructkey.domain.Team;
import com.veilsun.constructkey.domain.User;
import com.veilsun.constructkey.specification.team.TeamByTeamIdSpec;
import com.veilsun.constructkey.specification.team.TeamMembersByTeamIdSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veilsun.constructkey.domain.TeamMember;
import com.veilsun.constructkey.service.TeamService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/team/{teamId}")
public class TeamController {

	Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	TeamService teamService;
	
	@GetMapping("")
	public ResponseEntity<?> getTeam(@PathVariable("teamId") UUID teamId,
									 TeamByTeamIdSpec spec,
									 @RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<Team>(teamService.getTeam(spec, paths), HttpStatus.OK);
	}
	
	@GetMapping("/pool")
	public ResponseEntity<?> getTeamUserPool(@PathVariable("teamId") UUID teamId, Pageable page) {
		return new ResponseEntity<Page<User>>(teamService.findAllUserPool(teamId, page), HttpStatus.OK);
	}
	
	@GetMapping("/member")
	public ResponseEntity<?> getTeamMembers(@PathVariable("teamId") UUID teamId,
											TeamMembersByTeamIdSpec spec,
											@RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<List<TeamMember>>(teamService.getTeamMembers(spec, paths), HttpStatus.OK);
	}
	
	@PostMapping("/member")
	public ResponseEntity<?> addTeamMember(@PathVariable("teamId") UUID teamId, @RequestBody TeamMember member) {
		return new ResponseEntity<TeamMember>(teamService.addTeamMember(teamId, member), HttpStatus.CREATED);
	}
	
	@PutMapping("/member/{memberId}")
	public ResponseEntity<?> updateTeamMember(
			@PathVariable("teamId") UUID teamId,
			@PathVariable("memberId") UUID memberId,
			@RequestBody TeamMember member) {
		return new ResponseEntity<TeamMember>(teamService.updateTeamMember(teamId, memberId, member), HttpStatus.OK);
	}
	
	@DeleteMapping("/member/{memberId}")
	public ResponseEntity<?> deleteTeamMember(
			@PathVariable("teamId") UUID teamId,
			@PathVariable("memberId") UUID memberId) {
		return new ResponseEntity<Boolean>(teamService.deleteTeamMember(memberId), HttpStatus.OK);
	}
}
