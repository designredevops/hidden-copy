package com.veilsun.constructkey.repository;

import com.veilsun.constructkey.domain.Organization;
import com.veilsun.constructkey.domain.ProjectOrganization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectOrganizationRepository extends JpaRepository<ProjectOrganization, UUID> {
    Page<ProjectOrganization> findAllByOrganizationIdAndProjectId(UUID orgId, UUID projectId, Pageable page);
}
