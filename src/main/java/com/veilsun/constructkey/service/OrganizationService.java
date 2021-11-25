package com.veilsun.constructkey.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.domain.Organization;
import com.veilsun.constructkey.domain.Team;
import com.veilsun.constructkey.domain.Team.TeamType;
import com.veilsun.constructkey.domain.dto.UserOrganizationInvitation;
import com.veilsun.constructkey.repository.OrganizationRepository;

@Service
public class OrganizationService {


	@Autowired
	private OrganizationRepository organizationRepository;

	public Organization createOrganization(UUID userId, Organization org) {
		org.setAdminTeam(new Team(userId, TeamType.OrganizationAdmin));
		org.setMemberTeam(new Team(TeamType.OrganizationMember));
		Organization createdOrg = organizationRepository.save(org);
		return createdOrg;
	}

	public Organization getOrganizationById(UUID orgId) {
		return organizationRepository.findById(orgId).orElseThrow();
	}

	public Organization updateOrganization(String orgId, Organization org) {
		return organizationRepository.save(org);
	}

	public Organization inviteUser(UserOrganizationInvitation invitation) {
		//TODO: Check if the user is already added
		
		//TODO: Add user to organization members
		return null;
	}

	public Page<Organization> getOrganizationByParentId(UUID orgId, Pageable page) {
		return organizationRepository.findAllByParentOrganizationId(orgId, page);
	}

	public Boolean deleteOrganization(String orgId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
