package com.veilsun.constructkey.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.Sequence;
import com.veilsun.constructkey.domain.SequenceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SequenceRepository extends JpaRepository<Sequence, UUID>, EntityGraphJpaSpecificationExecutor<Sequence> {
    List<Sequence> findAllByPullPlanTargetId(UUID pptId);

}
