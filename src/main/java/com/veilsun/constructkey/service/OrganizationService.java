package com.veilsun.constructkey.service;

import java.util.UUID;

import com.veilsun.constructkey.domain.*;
import com.veilsun.constructkey.repository.PullPlanTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.Team.TeamType;
import com.veilsun.constructkey.domain.dto.UserOrganizationInvitation;
import com.veilsun.constructkey.repository.OrganizationRepository;

@Service
public class OrganizationService {


	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private PullPlanTargetRepository pullPlanTargetRepository;

	public Organization createOrganization(UUID userId, Organization org) {
		org.setAdminTeam(new Team(userId, TeamType.OrganizationAdmin));
		org.setMemberTeam(new Team(TeamType.OrganizationMember));
		org.setDisplayStyle(new DisplayStyle());
		org.setWorkSchedule(new WorkSchedule());
		Organization createdOrg = organizationRepository.save(org);

		return createdOrg;
	}

	public Organization findOneById(UUID orgId) {
		return organizationRepository.findById(orgId).orElseThrow();
	}

	public Page<PullPlanTarget> getPPTByOrganization(UUID orgId, Pageable page) {
		return pullPlanTargetRepository.findAllByProjectOrganizationId(orgId, page);
	}

	public Organization updateOrganization(UUID orgId, Organization org) {
		Organization originalOrganization = organizationRepository.findById(orgId).orElseThrow();
		if (org.getName() != null) originalOrganization.setName(org.getName());
		if (org.getAllowExternalInvites() != null) originalOrganization.setAllowExternalInvites(org.getAllowExternalInvites());

		return organizationRepository.save(originalOrganization);
	}

	public Organization inviteUser(UserOrganizationInvitation invitation) {
		//TODO: Check if the user is already added
		
		//TODO: Add user to organization members
		return null;
	}

	public Page<Organization> findAllByParentId(UUID orgId, Pageable page) {
		return organizationRepository.findAllByParentOrganizationId(orgId, page);
	}

	public Boolean deleteOrganization(UUID orgId) {
		// TODO Auto-generated method stub
		organizationRepository.deleteById(orgId);
		return true;
	}

	public Organization createSubOrganization(UUID uid, UUID parentOrgId, Organization org) {
		org.setAdminTeam(new Team(uid, TeamType.OrganizationAdmin));
		org.setMemberTeam(new Team(TeamType.OrganizationMember));
		org.setDisplayStyle(new DisplayStyle());
		org.setWorkSchedule(new WorkSchedule());
		org.setParentOrganization(new Organization(parentOrgId));
		Organization createdOrg = organizationRepository.save(org);

		return createdOrg;
	}
}
