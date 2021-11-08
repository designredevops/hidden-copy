package com.veilsun.constructkey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.Project;
import com.veilsun.constructkey.domain.PullPlanTarget;
import com.veilsun.constructkey.repository.PullPlanTargetMeetingRepository;
import com.veilsun.constructkey.repository.PullPlanTargetRepository;

@Service
public class PullPlanTargetService {


	@Autowired
	private PullPlanTargetRepository pptRepository;
	
	@Autowired
	private PullPlanTargetMeetingRepository pptMeetingRepository;

	public Page<Project> getPPTByProject(String orgId, String projectId, Pageable page) {
		return null;
	}

	public PullPlanTarget getProjectById(String orgId, String projectId, String pptId) {
		return pptRepository.findOneByIdAndProjectIdAndProjectOrganizationId(pptId, projectId, orgId).orElseThrow();
	}

	

}
