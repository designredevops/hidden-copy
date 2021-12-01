package com.veilsun.constructkey.repository;

import com.veilsun.constructkey.domain.Project;
import com.veilsun.constructkey.domain.ProjectLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectLocationRepository extends JpaRepository<ProjectLocation, UUID> {

	Page<ProjectLocation> findAllByProjectIdAndProjectOrganizationId(UUID projectId, UUID orgId, Pageable page);

	ProjectLocation findByProjectIdAndId(UUID projectId, UUID locationId);
}
