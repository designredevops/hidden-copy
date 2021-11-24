package com.veilsun.constructkey.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

	public Page<Organization> findAllByMemberTeamMembersUserId(UUID userId, Pageable page);

	public Page<Organization> findAllByParentOrganizationId(String orgId, Pageable page);
	
}
