package com.veilsun.constructkey.repository;

import com.veilsun.constructkey.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
}