package com.veilsun.constructkey.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.ProjectOrganization;

@Repository
public interface ProjectOrganizationRepository extends JpaRepository<ProjectOrganization, UUID>, EntityGraphJpaSpecificationExecutor<ProjectOrganization> {
    Page<ProjectOrganization> findAllByOrganizationIdAndProjectId(UUID orgId, UUID projectId, Pageable page);
}
