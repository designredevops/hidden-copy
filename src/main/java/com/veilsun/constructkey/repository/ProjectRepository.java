package com.veilsun.constructkey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.veilsun.constructkey.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

	public Page<Project> findAllByMemberTeamMembersUserId(String userId, Pageable page);
	
}
