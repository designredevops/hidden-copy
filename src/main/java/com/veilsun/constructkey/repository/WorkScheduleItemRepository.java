package com.veilsun.constructkey.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.WorkScheduleItem;

@Repository
public interface WorkScheduleItemRepository extends JpaRepository<WorkScheduleItem, UUID>, EntityGraphJpaSpecificationExecutor<WorkScheduleItem> {
        Page<WorkScheduleItem> findAllByWorkScheduleId(UUID workScheduleId, Pageable page);
	
}
