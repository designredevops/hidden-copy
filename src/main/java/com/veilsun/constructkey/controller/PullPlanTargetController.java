package com.veilsun.constructkey.controller;

import com.veilsun.constructkey.domain.*;
import com.veilsun.constructkey.specification.ppt.PullPlanTargetByProjectIdSpec;
import com.veilsun.constructkey.specification.ppt.PullPlanTargetByPullPlanTargetIdSpec;
import com.veilsun.constructkey.specification.ppt.chutes.PullPlanTargetChuteByPullPlanTargetChuteIdSpec;
import com.veilsun.constructkey.specification.ppt.chutes.PullPlanTargetChutesByPullPlanTargetIdSpec;
import com.veilsun.constructkey.specification.ppt.chutes.cards.CardByCardIdSpec;
import com.veilsun.constructkey.specification.ppt.chutes.cards.CardsByPullPlanTargetChuteSpec;
import com.veilsun.constructkey.specification.ppt.meetings.PullPlanTargetMeetingByPullPlanTargetMeetingIdSpec;
import com.veilsun.constructkey.specification.ppt.meetings.PullPlanTargetMeetingsByPullPlanTargetIdSpec;
import com.veilsun.constructkey.specification.sequence.SequencesByPullPlanTargetIdSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veilsun.constructkey.service.PullPlanTargetService;

import javax.validation.Valid;
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
			Pageable page,
			PullPlanTargetByProjectIdSpec spec,
			@RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<Page<PullPlanTarget>>(pptService.findAllByProjectId(spec, page, paths), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createPPT(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			@Valid @RequestBody PullPlanTarget ppt) {
		return new ResponseEntity<PullPlanTarget>(pptService.createPPT(orgId, projectId, ppt), HttpStatus.CREATED);
	}
	
	@GetMapping("/{pptId}")
	public ResponseEntity<?> getPPT(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			PullPlanTargetByPullPlanTargetIdSpec spec,
			@RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<PullPlanTarget>(pptService.findOneById(
				spec, paths), HttpStatus.OK);
	}
	
	@PutMapping("/{pptId}")
	public ResponseEntity<?> updatePPT(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@RequestBody PullPlanTarget ppt ) {
		return new ResponseEntity<PullPlanTarget>(pptService.updatePPT(pptId, ppt), HttpStatus.OK);
	}
	
	@DeleteMapping("/{pptId}")
	public ResponseEntity<?> deletePPT(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId) {
		return new ResponseEntity<Boolean>(pptService.deletePPT(pptId), HttpStatus.OK);
	}
	
	/**
	 * Board channel
	 * @throws Exception 
	 */
	
	@GetMapping("/{pptId}/channel")
	public ResponseEntity<?> getChannelAccessToken(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@ModelAttribute UUID userId) throws Exception {
		return new ResponseEntity<String>(pptService.getChannelAccessToken(pptId, userId), HttpStatus.OK);
	}
	
	/**
	 * PPT Meeting
	 */
	
	@GetMapping("/{pptId}/meeting")
	public ResponseEntity<?> getPPTMeetings(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			Pageable page,
			PullPlanTargetMeetingsByPullPlanTargetIdSpec spec,
			@RequestParam(name = "paths", required = false) String... paths
	) {
		return new ResponseEntity<Page<PullPlanTargetMeeting>>(pptService.findAllByPullPlanTargetId(
				spec, page, paths), HttpStatus.OK);
	}
	
	
	@PostMapping("/{pptId}/meeting")
	public ResponseEntity<?> createPPTMeeting(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@Valid @RequestBody PullPlanTargetMeeting pptMeeting) {
		return new ResponseEntity<PullPlanTargetMeeting>(
				pptService.createMeeting(orgId, projectId, pptId, pptMeeting),
				HttpStatus.CREATED);
	}
	
	@GetMapping("/{pptId}/meeting/{meetingId}")
	public ResponseEntity<?> getPPTMeeting(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@PathVariable() UUID meetingId,
			PullPlanTargetMeetingByPullPlanTargetMeetingIdSpec spec,
			@RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<PullPlanTargetMeeting>(pptService.findOneMeetingById(spec, paths), HttpStatus.OK);
	}
	
	@PutMapping("/{pptId}/meeting/{meetingId}")
	public ResponseEntity<?> updatePPTMeeting(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@PathVariable() UUID meetingId,
			@RequestBody PullPlanTargetMeeting pptMeeting ) {
		return new ResponseEntity<PullPlanTargetMeeting>(pptService.updateMeeting(meetingId, pptMeeting), HttpStatus.OK);
	}
	
	@DeleteMapping("/{pptId}/meeting/{meetingId}")
	public ResponseEntity<?> deletePPTMeeting(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@PathVariable() UUID meetingId) {
		return new ResponseEntity<Boolean>(pptService.deleteMeeting(meetingId), HttpStatus.OK);
	}
	
	/**
	 * PPT CHUTE
	 */
	
	@GetMapping("/{pptId}/chute")
	public ResponseEntity<?> getPPTChutes(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			Pageable page,
			PullPlanTargetChutesByPullPlanTargetIdSpec spec,
			@RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<Page<Chute>>(pptService.findAllChutesByPptId(spec, page, paths), HttpStatus.OK);
	}
	
	
	@PostMapping("/{pptId}/chute")
	public ResponseEntity<?> createPPTChute(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@Valid @RequestBody Chute chute) {
		return new ResponseEntity<Chute>(pptService.createChute(pptId, orgId, projectId, chute), HttpStatus.CREATED);
	}
	
	@GetMapping("/{pptId}/chute/{chuteId}")
	public ResponseEntity<?> getPPTChute(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@PathVariable() UUID chuteId,
			PullPlanTargetChuteByPullPlanTargetChuteIdSpec spec,
			@RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<Chute>(pptService.findOneByIdChute(spec, paths), HttpStatus.OK);
	}
	
	@PutMapping("/{pptId}/chute/{chuteId}")
	public ResponseEntity<?> updatePPTChute(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@PathVariable() UUID chuteId,
			@RequestBody Chute chute ) {
		return new ResponseEntity<Chute>(pptService.updateChute(chuteId, chute), HttpStatus.OK);
	}
	
	@DeleteMapping("/{pptId}/chute/{chuteId}")
	public ResponseEntity<?> deletePPTChute(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@PathVariable() UUID chuteId) {
		return new ResponseEntity<Boolean>(pptService.deleteChute(chuteId), HttpStatus.OK);
	}
	
	/**
	 * PPT CHUTE CARD
	 */
	
	@GetMapping("/{pptId}/chute/{chuteId}/card")
	public ResponseEntity<?> getPPTChuteCards(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@PathVariable() UUID chuteId,
			Pageable page,
			CardsByPullPlanTargetChuteSpec spec,
			@RequestParam(name = "paths", required = false) String... paths
			) {
		return new ResponseEntity<Page<Card>>(pptService.findAllChuteCards(spec, page, paths), HttpStatus.OK);
	}
	
	
	@PostMapping("/{pptId}/chute/{chuteId}/card")
	public ResponseEntity<?> createPPTChuteCard(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@PathVariable() UUID chuteId,
			@Valid @RequestBody Card card) {
		return new ResponseEntity<Card>(pptService.createChuteCard(chuteId, card), HttpStatus.OK);
	}
	
	@GetMapping("/{pptId}/chute/{chuteId}/card/{cardId}")
	public ResponseEntity<?> getPPTChuteCard(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@PathVariable() UUID chuteId,
			@PathVariable() UUID cardId,
			CardByCardIdSpec spec,
			@RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<Card>(pptService.findOneByIdChuteCard(spec, paths), HttpStatus.OK);
	}
	
	@PutMapping("/{pptId}/chute/{chuteId}/card/{cardId}")
	public ResponseEntity<?> updatePPTChuteCard(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@PathVariable() UUID chuteId,
			@PathVariable() UUID cardId,
			@RequestBody Card card ) {
		return new ResponseEntity<Card>(pptService.updateChuteCard(pptId, chuteId, cardId, card), HttpStatus.OK);
	}
	
	@DeleteMapping("/{pptId}/chute/{chuteId}/card/{cardId}")
	public ResponseEntity<?> deletePPTChuteCard(
			@PathVariable() UUID orgId, 
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@PathVariable() UUID chuteId,
			@PathVariable() UUID cardId) {
		return new ResponseEntity<Boolean>(pptService.deleteChuteCard(cardId), HttpStatus.OK);
	}

	/**
	 * Sequence
	 * */

	@GetMapping("/{pptId}/sequence")
	public ResponseEntity<?> getPPTSequences(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			Pageable page,
			SequencesByPullPlanTargetIdSpec spec,
			@RequestParam(name = "paths", required = false) String... paths
	) {
		return new ResponseEntity<Page<Sequence>>(pptService.findAllSequencesByPptId(spec, page, paths), HttpStatus.OK);
	}

	@PostMapping("/{pptId}/sequence")
	public ResponseEntity<?> createPPTSequence(
			@PathVariable() UUID orgId,
			@PathVariable() UUID projectId,
			@PathVariable() UUID pptId,
			@Valid @RequestBody Sequence sequence) {
		return new ResponseEntity<Sequence>(
				pptService.createSequence(pptId, sequence), HttpStatus.CREATED);
	}
}
