package com.veilsun.constructkey.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.PullPlanTarget;
import com.veilsun.constructkey.domain.SequenceItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SequenceItemRepository extends JpaRepository<SequenceItem, UUID>, EntityGraphJpaSpecificationExecutor<SequenceItem> {

    Page<SequenceItem> findAllBySequenceId(UUID secId, Pageable page);
}
