package com.veilsun.constructkey.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.Card;
import com.veilsun.constructkey.domain.Chute;
import com.veilsun.constructkey.domain.Project;
import com.veilsun.constructkey.domain.PullPlanTarget;
import com.veilsun.constructkey.domain.PullPlanTargetMeeting;
import com.veilsun.constructkey.service.PullPlanTargetService;

import java.util.UUID;

@RestController
@RequestMapping("/org/{orgId}/project/{projectId}/pull-plan-target")
public class PullPlanTargetController {

	Logger logger = LoggerFactory.getLogger(PullPlanTargetController.class);
	
	@Autowired
	private PullPlanTargetService pptService;
	
	@GetMapping("")
	public ResponseEntity<?> getPPTByProject(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			Pageable page) {
		return new ResponseEntity<Page<Project>>(pptService.getPPTByProject(orgId, projectId, page), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createPPT(
			@PathVariable() String orgId, 
			@PathVariable() String projectId, 
			@RequestBody PullPlanTarget ppt) {
		return ResponseEntity.ok(new PullPlanTarget());
	}
	
	@GetMapping("/{pptId}")
	public ResponseEntity<?> getPPT(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId) {
		return new ResponseEntity<PullPlanTarget>(pptService.getProjectById(orgId, projectId, pptId), HttpStatus.OK);
	}
	
	@PutMapping("/{pptId}")
	public ResponseEntity<?> updatePPT(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@RequestBody PullPlanTarget ppt ) {
		return null;
	}
	
	@DeleteMapping("/{pptId}")
	public ResponseEntity<?> deletePPT(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId) {
		return null;
	}
	
	/**
	 * PPT Meeting
	 */
	
	@GetMapping("/{pptId}/meeting")
	public ResponseEntity<?> getPPTMeetings(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId) {
		return null;
	}
	
	
	@PostMapping("/{pptId}/meeting")
	public ResponseEntity<?> createPPTMeeting(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@RequestBody PullPlanTargetMeeting pptMeeting) {
		return ResponseEntity.ok(new PullPlanTargetMeeting());
	}
	
	@GetMapping("/{pptId}/meeting/{meetingId}")
	public ResponseEntity<?> getPPTMeeting(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@PathVariable() String meetingId) {
		return null;
	}
	
	@PutMapping("/{pptId}/meeting/{meetingId}")
	public ResponseEntity<?> updatePPTMeeting(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@PathVariable() String meetingId,
			@RequestBody PullPlanTargetMeeting pptMeeting ) {
		return null;
	}
	
	@DeleteMapping("/{pptId}/meeting/{meetingId}")
	public ResponseEntity<?> deletePPTMeeting(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@PathVariable() String meetingId) {
		return null;
	}
	
	/**
	 * PPT CHUTE
	 */
	
	@GetMapping("/{pptId}/chute")
	public ResponseEntity<?> getPPTChutes(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId) {
		return null;
	}
	
	
	@PostMapping("/{pptId}/chute")
	public ResponseEntity<?> createPPTChute(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@RequestBody Chute chute) {
		return ResponseEntity.ok(new Chute());
	}
	
	@GetMapping("/{pptId}/chute/{chuteId}")
	public ResponseEntity<?> getPPTChute(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@PathVariable() String chuteId) {
		return null;
	}
	
	@PutMapping("/{pptId}/chute/{chuteId}")
	public ResponseEntity<?> updatePPTChute(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@PathVariable() String chuteId,
			@RequestBody Chute chute ) {
		return null;
	}
	
	@DeleteMapping("/{pptId}/chute/{chuteId}")
	public ResponseEntity<?> deletePPTChute(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@PathVariable() String chuteId) {
		return null;
	}
	
	/**
	 * PPT CHUTE CARD
	 */
	
	@GetMapping("/{pptId}/chute/{chuteId}/card")
	public ResponseEntity<?> getPPTChuteCards(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@PathVariable() String chuteId) {
		return null;
	}
	
	
	@PostMapping("/{pptId}/chute/{chuteId}/card")
	public ResponseEntity<?> createPPTChuteCard(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@RequestBody Card card) {
		return ResponseEntity.ok(new Card());
	}
	
	@GetMapping("/{pptId}/chute/{chuteId}/card/{cardId}")
	public ResponseEntity<?> getPPTChuteCard(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@PathVariable() String chuteId,
			@PathVariable() String cardId) {
		return null;
	}
	
	@PutMapping("/{pptId}/chute/{chuteId}/card/{cardId}")
	public ResponseEntity<?> updatePPTChuteCard(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@PathVariable() String chuteId,
			@PathVariable() String cardId,
			@RequestBody Card card ) {
		return null;
	}
	
	@DeleteMapping("/{pptId}/chute/{chuteId}/card/{cardId}")
	public ResponseEntity<?> deletePPTChuteCard(
			@PathVariable() String orgId, 
			@PathVariable() String projectId,
			@PathVariable() String pptId,
			@PathVariable() String chuteId,
			@PathVariable() String cardId) {
		return null;
	}
}
