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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.Project;
import com.veilsun.constructkey.domain.PullPlanTarget;
import com.veilsun.constructkey.domain.PullPlanTargetMeeting;
import com.veilsun.constructkey.service.PullPlanTargetService;

@RestController
@RequestMapping("/org/{orgId}/project/{projectId}/ppt")
public class PullPlanTargetController {

	Logger logger = LoggerFactory.getLogger(PullPlanTargetController.class);
	
	@Autowired
	private PullPlanTargetService pptService;
	
	@GetMapping("")
	public ResponseEntity<?> getPPTByProject(
			@RequestParam() String orgId, 
			@RequestParam() String projectId, 
			Pageable page) {
		return new ResponseEntity<Page<Project>>(pptService.getPPTByProject(orgId, projectId, page), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createPPT(
			@RequestParam() String orgId, 
			@RequestParam() String projectId, 
			@RequestBody PullPlanTarget ppt) {
		return ResponseEntity.ok(new PullPlanTarget());
	}
	
	@GetMapping("/{pptId}")
	public ResponseEntity<?> getPPT(
			@RequestParam() String orgId, 
			@RequestParam() String projectId,
			@RequestParam() String pptId) {
		return new ResponseEntity<PullPlanTarget>(pptService.getProjectById(orgId, projectId, pptId), HttpStatus.OK);
	}
	
	@PutMapping("/{pptId}")
	public ResponseEntity<?> updatePPT(
			@RequestParam() String orgId, 
			@RequestParam() String projectId,
			@RequestParam() String pptId,
			@RequestBody PullPlanTarget ppt ) {
		return null;
	}
	
	@DeleteMapping("/{pptId}")
	public ResponseEntity<?> deletePPT(
			@RequestParam() String orgId, 
			@RequestParam() String projectId,
			@RequestParam() String pptId) {
		return null;
	}
	
	/**
	 * PPT Meeting
	 */
	
	@GetMapping("/{pptId}/meeting")
	public ResponseEntity<?> getPPTMeetings(
			@RequestParam() String orgId, 
			@RequestParam() String projectId,
			@RequestParam() String pptId) {
		return null;
	}
	
	
	@PostMapping("/{pptId}/meeting")
	public ResponseEntity<?> createPPTMeeting(
			@RequestParam() String orgId, 
			@RequestParam() String projectId,
			@RequestParam() String pptId,
			@RequestBody PullPlanTargetMeeting pptMeeting) {
		return ResponseEntity.ok(new PullPlanTargetMeeting());
	}
	
	@GetMapping("/{pptId}/meeting/{meetingId}")
	public ResponseEntity<?> getPPTMeeting(
			@RequestParam() String orgId, 
			@RequestParam() String projectId,
			@RequestParam() String pptId,
			@RequestParam() String meetingId) {
		return null;
	}
	
	@PutMapping("/{pptId}")
	public ResponseEntity<?> updatePPTMeeting(
			@RequestParam() String orgId, 
			@RequestParam() String projectId,
			@RequestParam() String pptId,
			@RequestParam() String meetingId,
			@RequestBody PullPlanTargetMeeting pptMeeting ) {
		return null;
	}
	
	@DeleteMapping("/{pptId}")
	public ResponseEntity<?> deletePPTMeeting(
			@RequestParam() String orgId, 
			@RequestParam() String projectId,
			@RequestParam() String pptId,
			@RequestParam() String meetingId) {
		return null;
	}
}
