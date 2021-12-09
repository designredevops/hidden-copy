package com.veilsun.constructkey.service;

import com.veilsun.constructkey.domain.Bucket;
import com.veilsun.constructkey.domain.PullPlanTargetMeeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.Project;
import com.veilsun.constructkey.domain.PullPlanTarget;
import com.veilsun.constructkey.repository.PullPlanTargetMeetingRepository;
import com.veilsun.constructkey.repository.PullPlanTargetRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
public class PullPlanTargetService {


	@Autowired
	private PullPlanTargetRepository pptRepository;
	
	@Autowired
	private PullPlanTargetMeetingRepository pptMeetingRepository;

	public Page<PullPlanTarget> getPPTByProject(UUID orgId, UUID projectId, Pageable page) {
		return pptRepository.findAllByProjectId(projectId, page);
	}

	public PullPlanTarget getProjectById(UUID orgId, UUID projectId, UUID pptId) {
		return pptRepository.findOneByIdAndProjectIdAndProjectOrganizationId(pptId, projectId, orgId).orElseThrow();
	}

	public PullPlanTarget createPPT(UUID orgId, UUID projectId, PullPlanTarget ppt) {
		ppt.setProject(new Project(projectId));
		ppt.setDocuments(new Bucket());
		PullPlanTarget createdPPT = pptRepository.save(ppt);
		return createdPPT;
	}

	public PullPlanTarget updatePPT(UUID pptId, PullPlanTarget ppt) {
		PullPlanTarget originalPPT = pptRepository.findById(pptId).orElseThrow();
		if (ppt.getName() != null) originalPPT.setName(ppt.getName());
		if (ppt.getDescription() != null) originalPPT.setDescription(ppt.getDescription());
		if (ppt.getDuration() != null) originalPPT.setDuration(ppt.getDuration());
		if (ppt.getCompletionDate() != null) originalPPT.setCompletionDate(ppt.getCompletionDate());

		pptRepository.save(originalPPT);
		return originalPPT;
	}

	public Boolean deletePPT(UUID pptId) {
		pptRepository.deleteById(pptId);
		return true;
	}

	public Page<PullPlanTargetMeeting> getPPTMeetings(UUID orgId, UUID projectId, UUID pptId, Pageable page) {
		return pptMeetingRepository.findAllByPullPlanTargetId(pptId, page);
	}

	public PullPlanTargetMeeting createPPTMeeting(UUID orgId, UUID projectId, UUID pptId, PullPlanTargetMeeting pptMeeting) {
		pptMeeting.setPullPlanTarget(new PullPlanTarget(pptId));
		PullPlanTargetMeeting createdPPTMeeting = pptMeetingRepository.save(pptMeeting);
		return createdPPTMeeting;
	}

	public PullPlanTargetMeeting getPPTMeeting(UUID meetingId) {
		return pptMeetingRepository.findById(meetingId).orElseThrow();
	}

	public PullPlanTargetMeeting updatePPTMeeting(UUID meetingId, PullPlanTargetMeeting pptMeeting) {
		PullPlanTargetMeeting originalPPTMeeting = pptMeetingRepository.findById(meetingId).orElseThrow();
		if (pptMeeting.getTitle() != null) originalPPTMeeting.setTitle(pptMeeting.getTitle());
		if (pptMeeting.getLocation() != null) originalPPTMeeting.setLocation(pptMeeting.getLocation());
		if (pptMeeting.getStarts() != null) originalPPTMeeting.setStarts(pptMeeting.getStarts());
		if (pptMeeting.getEnds() != null) originalPPTMeeting.setEnds(pptMeeting.getEnds());

		pptMeetingRepository.save(originalPPTMeeting);
		return originalPPTMeeting;
	}

	public Boolean deletePPTMeeting(UUID meetingId) {
		pptMeetingRepository.deleteById(meetingId);
		return true;
	}
}
