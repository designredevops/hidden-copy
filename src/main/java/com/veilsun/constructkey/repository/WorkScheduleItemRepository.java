package com.veilsun.constructkey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.WorkScheduleItem;

import java.util.UUID;

@Repository
public interface WorkScheduleItemRepository extends JpaRepository<WorkScheduleItem, UUID> {
        Page<WorkScheduleItem> findAllByWorkScheduleId(UUID workScheduleId, Pageable page);
	
}
