package com.veilsun.constructkey.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID>, EntityGraphJpaSpecificationExecutor<Project> {

	public Page<Project> findAllByMemberTeamMembersUserId(UUID userId, Pageable page);

	public Page<Project> findAllByOrganizationId(UUID orgId, Pageable page);
	
}
