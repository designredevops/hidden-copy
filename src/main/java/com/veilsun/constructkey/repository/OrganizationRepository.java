package com.veilsun.constructkey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.veilsun.constructkey.domain.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, String> {

	public Page<Organization> findAllByMemberTeamMembersUserId(String userId, Pageable page);

	public Page<Organization> findAllByParentOrganizationId(String orgId);
	
}
