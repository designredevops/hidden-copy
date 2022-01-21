package com.veilsun.constructkey.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.DisplayStyle;
import com.veilsun.constructkey.domain.Organization;
import com.veilsun.constructkey.domain.PullPlanTarget;
import com.veilsun.constructkey.domain.Team;
import com.veilsun.constructkey.domain.Team.TeamType;
import com.veilsun.constructkey.domain.TeamMember.TeamMemberRole;
import com.veilsun.constructkey.domain.WorkSchedule;
import com.veilsun.constructkey.domain.dto.UserOrganizationInvitation;
import com.veilsun.constructkey.repository.OrganizationRepository;
import com.veilsun.constructkey.repository.PullPlanTargetRepository;
import com.veilsun.constructkey.specification.organization.OrganizationByParentSpec;
import com.veilsun.constructkey.specification.ppt.PullPlanTargetByOrganizationSpec;
import com.veilsun.constructkey.utils.EGUtils;

@Service
public class OrganizationService {


	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private PullPlanTargetRepository pullPlanTargetRepository;

	public Organization createOrganization(UUID userId, Organization org) {
		org.setMemberTeam(new Team(userId, TeamType.OrganizationMember, TeamMemberRole.Admin));
		org.setDisplayStyle(new DisplayStyle());
		org.setWorkSchedule(new WorkSchedule());
		Organization createdOrg = organizationRepository.save(org);

		return createdOrg;
	}

	public Organization findOneById(UUID orgId) {
		return organizationRepository.findById(orgId).orElseThrow();
	}
	
	public Organization findOne(Specification<Organization> spec, String... paths) {
		return organizationRepository.findOne(spec, EGUtils.fromAttributePaths(paths)).orElseThrow();		
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

	@PreAuthorize("@authenticationService.isOrganizationAdmin(#orgId)")
	public Boolean deleteOrganization(UUID orgId) {
		organizationRepository.deleteById(orgId);
		return true;
	}

	public Organization createSubOrganization(UUID uid, UUID parentOrgId, Organization org) {
		org.setMemberTeam(new Team(TeamType.OrganizationMember));
		org.setDisplayStyle(new DisplayStyle());
		org.setWorkSchedule(new WorkSchedule());
		org.setParentOrganization(new Organization(parentOrgId));
		Organization createdOrg = organizationRepository.save(org);

		return createdOrg;
	}

	public Page<PullPlanTarget> getPPTByOrganization(PullPlanTargetByOrganizationSpec spec, String[] paths,
			Pageable page) {
		return pullPlanTargetRepository.findAll(spec, page, EGUtils.fromAttributePaths(paths));
	}

	public Page<Organization> findAllByParentId(OrganizationByParentSpec spec, String[] paths, Pageable page) {
		return organizationRepository.findAll(spec, page, EGUtils.fromAttributePaths(paths));
	}
}
