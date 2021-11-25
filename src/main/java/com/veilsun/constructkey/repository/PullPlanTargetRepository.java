package com.veilsun.constructkey.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.PullPlanTarget;

@Repository
public interface PullPlanTargetRepository extends JpaRepository<PullPlanTarget, UUID> {

	Optional<PullPlanTarget> findOneByIdAndProjectIdAndProjectOrganizationId(UUID pptId, UUID projectId, UUID orgId);

}
