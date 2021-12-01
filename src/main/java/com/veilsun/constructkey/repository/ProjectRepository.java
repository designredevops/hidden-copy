package com.veilsun.constructkey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.Project;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

	public Page<Project> findAllByMemberTeamMembersUserId(UUID userId, Pageable page);

	public Page<Project> findAllByOrganizationId(UUID orgId, Pageable page);
	
}
