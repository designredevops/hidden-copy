package com.veilsun.constructkey.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.TeamMember;
import com.veilsun.constructkey.service.TeamService;

@RestController
@RequestMapping("/team/{teamId}")
public class TeamController {

	Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	TeamService teamService;
	
	@GetMapping("")
	public ResponseEntity<?> getTeam(@RequestParam("teamId") String teamId) {
		return null;
	}
	
	@GetMapping("/pool")
	public ResponseEntity<?> getTeamUserPool(@RequestParam("teamId") String teamId) {
		return null;
	}
	
	@GetMapping("/member")
	public ResponseEntity<?> getTeamMembers(@RequestParam("teamId") String teamId) {
		return null;
	}
	
	@PostMapping("/member")
	public ResponseEntity<?> addTeamMember(@RequestParam("teamId") String teamId, @RequestBody TeamMember member) {
		return null;
	}
	
	@PutMapping("/member/{memberId}")
	public ResponseEntity<?> updateTeamMember(
			@RequestParam("teamId") String teamId, 
			@RequestParam("memberId") String memberId,
			@RequestBody TeamMember member) {
		return null;
	}
	
	@DeleteMapping("/member/{memberId}")
	public ResponseEntity<?> deleteTeamMember(
			@RequestParam("teamId") String teamId, 
			@RequestParam("memberId") String memberId) {
		return null;
	}
}
