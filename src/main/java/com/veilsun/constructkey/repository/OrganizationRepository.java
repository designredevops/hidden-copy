package com.veilsun.constructkey.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID>, JpaSpecificationExecutor<Organization>, EntityGraphJpaSpecificationExecutor<Organization> {

	public Page<Organization> findAllByMemberTeamMembersUserId(UUID userId, Pageable page);

	public Page<Organization> findAllByMemberTeamMembersUserIdOrAdminTeamMembersUserId(UUID userId, UUID secondUserId, Pageable page, Specification<Organization> spec);

	public Page<Organization> findAllByParentOrganizationId(UUID orgId, Pageable page);

	public Organization findOneByAdminTeamId(UUID adminTeamId);

	public Organization findOneByMemberTeamId(UUID memberTeamId);
	
	@Query(name = "SELECT o.id FROM organization o INNER JOIN team_member tm ON tm.team_id = o.admin_team_id WHERE o.id = :orgId AND tm.user_id = :userId LIMIT 1",
		   nativeQuery = true)
	public Optional<UUID> memberOfOrganizationAdmin(@Param("orgId") UUID orgId, @Param("userId") UUID userId);
	
}
