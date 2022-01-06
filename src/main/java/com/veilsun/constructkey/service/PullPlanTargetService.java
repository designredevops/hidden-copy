package com.veilsun.constructkey.service;

import com.veilsun.constructkey.domain.*;
import com.veilsun.constructkey.domain.Team.TeamType;
import com.veilsun.constructkey.repository.CardRepository;
import com.veilsun.constructkey.repository.ChuteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.repository.PullPlanTargetMeetingRepository;
import com.veilsun.constructkey.repository.PullPlanTargetRepository;

import java.util.UUID;

@Service
public class PullPlanTargetService {


	@Autowired
	private PullPlanTargetRepository pptRepository;
	
	@Autowired
	private PullPlanTargetMeetingRepository pptMeetingRepository;

	@Autowired
	private ChuteRepository pptChuteRepository;

	@Autowired
	private CardRepository pptChuteCardRepository;

	public Page<PullPlanTarget> findAllByProjectId(UUID projectId, Pageable page) {
		return pptRepository.findAllByProjectId(projectId, page);
	}

	public PullPlanTarget findOneByIdAndProjectIdAndOrganizationId(UUID pptId, UUID projectId, UUID orgId) {
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

	public Page<PullPlanTargetMeeting> findAllByPullPlanTargetId(UUID orgId, UUID projectId, UUID pptId, Pageable page) {
		return pptMeetingRepository.findAllByPullPlanTargetId(pptId, page);
	}

	public PullPlanTargetMeeting createMeeting(UUID orgId, UUID projectId, UUID pptId, PullPlanTargetMeeting pptMeeting) {
		pptMeeting.setPullPlanTarget(new PullPlanTarget(pptId));
		pptMeeting.setInvitees(new Team(TeamType.PullPlanTargetMeeting));
		PullPlanTargetMeeting createdPPTMeeting = pptMeetingRepository.save(pptMeeting);
		return createdPPTMeeting;
	}

	public PullPlanTargetMeeting findOneMeetingById(UUID meetingId) {
		return pptMeetingRepository.findById(meetingId).orElseThrow();
	}

	public PullPlanTargetMeeting updateMeeting(UUID meetingId, PullPlanTargetMeeting pptMeeting) {
		PullPlanTargetMeeting originalPPTMeeting = pptMeetingRepository.findById(meetingId).orElseThrow();
		if (pptMeeting.getTitle() != null) originalPPTMeeting.setTitle(pptMeeting.getTitle());
		if (pptMeeting.getLocation() != null) originalPPTMeeting.setLocation(pptMeeting.getLocation());
		if (pptMeeting.getStarts() != null) originalPPTMeeting.setStarts(pptMeeting.getStarts());
		if (pptMeeting.getEnds() != null) originalPPTMeeting.setEnds(pptMeeting.getEnds());

		pptMeetingRepository.save(originalPPTMeeting);
		return originalPPTMeeting;
	}

	public Boolean deleteMeeting(UUID meetingId) {
		pptMeetingRepository.deleteById(meetingId);
		return true;
	}

	public Page<Chute> findAllChutesByPptId(UUID pptId, Pageable page) {
		return pptChuteRepository.findAllByPptId(pptId, page);
	}

	public Chute updateChute(UUID chuteId, Chute chute) {
		Chute originalChute = pptChuteRepository.findById(chuteId).orElseThrow();
		if (chute.getName() != null) originalChute.setName(chute.getName());
		pptChuteRepository.save(originalChute);
		return originalChute;
	}

	public Chute createChute(UUID pptId, UUID orgId, UUID projectId, Chute chute) {
		chute.setPpt(new PullPlanTarget(pptId));
		chute.setOrganization(new Organization(orgId));
		chute.setDisplayStyle(new DisplayStyle());
		Chute createdChute = pptChuteRepository.save(chute);
		return createdChute;
	}

	public Chute findOneByIdChute(UUID chuteId) {
		return pptChuteRepository.findById(chuteId).orElseThrow();
	}

	public Boolean deleteChute(UUID chuteId) {
		pptChuteRepository.deleteById(chuteId);
		return true;
	}

	public Card updateChuteCard(UUID chuteId, UUID cardId, Card card) {
		Card originalCard = pptChuteCardRepository.findById(cardId).orElseThrow();
		if (card.getDays() != null) originalCard.setDays(card.getDays());
		if (card.getPeople() != null) originalCard.setPeople(card.getPeople());
		if (card.getPromise() != null) originalCard.setPromise(card.getPromise());
		if (card.getNeed() != null) originalCard.setNeed(card.getNeed());
		if (card.getRanking() != null) originalCard.setRanking(card.getRanking());

		return pptChuteCardRepository.save(originalCard);
	}

	public Page<Card> findAllChuteCards(UUID chuteId, Pageable page) {
		return pptChuteCardRepository.findAllByChuteId(chuteId, page);
	}

	public Card createChuteCard(UUID chuteId, Card card) {
		card.setChute(new Chute(chuteId));
		Card createdCard = pptChuteCardRepository.save(card);
		return createdCard;
	}

	public Card findOneByIdChuteCard(UUID cardId) {
		return pptChuteCardRepository.findById(cardId).orElseThrow();
	}

	public Boolean deleteChuteCard(UUID cardId) {
		pptChuteCardRepository.deleteById(cardId);
		return true;
	}

}
