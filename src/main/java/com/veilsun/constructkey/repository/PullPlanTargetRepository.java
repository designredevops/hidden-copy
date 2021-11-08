package com.veilsun.constructkey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veilsun.constructkey.domain.PullPlanTarget;

public interface PullPlanTargetRepository extends JpaRepository<PullPlanTarget, String> {

	Optional<PullPlanTarget> findOneByIdAndProjectIdAndProjectOrganizationId(String pptId, String projectId, String orgId);

}
