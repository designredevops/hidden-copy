package com.veilsun.constructkey.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.Sequence;

public interface SequenceRepository extends JpaRepository<Sequence, UUID>, EntityGraphJpaSpecificationExecutor<Sequence> {
    List<Sequence> findAllByPullPlanTargetId(UUID pptId);

}
