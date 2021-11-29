package com.veilsun.constructkey.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.TeamMember;
import com.veilsun.constructkey.service.TeamService;

import java.util.UUID;

@RestController
@RequestMapping("/team/{teamId}")
public class TeamController {

	Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	TeamService teamService;
	
	@GetMapping("")
	public ResponseEntity<?> getTeam(@PathVariable("teamId") UUID teamId) {
		return null;
	}
	
	@GetMapping("/pool")
	public ResponseEntity<?> getTeamUserPool(@PathVariable("teamId") UUID teamId) {
		return null;
	}
	
	@GetMapping("/member")
	public ResponseEntity<?> getTeamMembers(@PathVariable("teamId") UUID teamId) {
		return null;
	}
	
	@PostMapping("/member")
	public ResponseEntity<?> addTeamMember(@PathVariable("teamId") UUID teamId, @RequestBody TeamMember member) {
		return null;
	}
	
	@PutMapping("/member/{memberId}")
	public ResponseEntity<?> updateTeamMember(
			@PathVariable("teamId") UUID teamId,
			@PathVariable("memberId") UUID memberId,
			@RequestBody TeamMember member) {
		return null;
	}
	
	@DeleteMapping("/member/{memberId}")
	public ResponseEntity<?> deleteTeamMember(
			@PathVariable("teamId") UUID teamId,
			@PathVariable("memberId") UUID memberId) {
		return null;
	}
}
