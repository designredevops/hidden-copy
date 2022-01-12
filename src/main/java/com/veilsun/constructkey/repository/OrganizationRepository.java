package com.veilsun.constructkey.repository;

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

	public Page<Organization> findAllByParentOrganizationId(UUID orgId, Pageable page);

	public Organization findOneByMemberTeamId(UUID memberTeamId);
	
	@Query(value = "SELECT 1 FROM organization o INNER JOIN team_member tm ON tm.team_id = o.member_team_id WHERE o.id = :orgId AND tm.user_id = :userId AND tm.role = 'Admin' LIMIT 1",
			   nativeQuery = true)
	public Integer isOrganizationAdmin(@Param("orgId") UUID orgId, @Param("userId") UUID userId);
	
	@Query(value = "SELECT 1 FROM organization o INNER JOIN team_member tm ON tm.team_id = o.member_team_id WHERE o.id = :orgId AND tm.user_id = :userId LIMIT 1",
		   nativeQuery = true)
	public Integer isOrganizationMember(@Param("orgId") UUID orgId, @Param("userId") UUID userId);
	
}
