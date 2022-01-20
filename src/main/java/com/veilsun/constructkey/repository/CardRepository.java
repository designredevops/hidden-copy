package com.veilsun.constructkey.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.Card;

public interface CardRepository extends JpaRepository<Card, UUID>, EntityGraphJpaSpecificationExecutor<Card> {
    public Page<Card> findAllByChuteId(UUID chuteId, Pageable page);
}