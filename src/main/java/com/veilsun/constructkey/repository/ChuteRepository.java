package com.veilsun.constructkey.repository;

import com.veilsun.constructkey.domain.Chute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChuteRepository extends JpaRepository<Chute, UUID> {
    public Page<Chute> findAllByPptId(UUID pptId, Pageable page);
}