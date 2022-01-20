package com.veilsun.constructkey.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.WorkSchedule;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, UUID>, EntityGraphJpaSpecificationExecutor<WorkSchedule> {

	
}
