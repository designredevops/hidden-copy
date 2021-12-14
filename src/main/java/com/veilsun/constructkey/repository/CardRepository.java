package com.veilsun.constructkey.repository;

import com.veilsun.constructkey.domain.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    public Page<Card> findAllByChuteId(UUID chuteId, Pageable page);
}