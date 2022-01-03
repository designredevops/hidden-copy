package com.veilsun.constructkey.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID>, JpaSpecificationExecutor<Organization> {

	public Page<Organization> findAllByMemberTeamMembersUserId(UUID userId, Pageable page);

	public Page<Organization> findAllByMemberTeamMembersUserIdOrAdminTeamMembersUserId(UUID userId, UUID secondUserId, Pageable page, Specification<Organization> spec);

	public Page<Organization> findAllByParentOrganizationId(UUID orgId, Pageable page);

	public Organization findOneByAdminTeamId(UUID adminTeamId);

	public Organization findOneByMemberTeamId(UUID memberTeamId);
	
}
