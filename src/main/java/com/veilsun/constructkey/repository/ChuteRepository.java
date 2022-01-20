package com.veilsun.constructkey.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.Chute;

public interface ChuteRepository extends JpaRepository<Chute, UUID>, EntityGraphJpaSpecificationExecutor<Chute> {
    public Page<Chute> findAllByPptId(UUID pptId, Pageable page);
}