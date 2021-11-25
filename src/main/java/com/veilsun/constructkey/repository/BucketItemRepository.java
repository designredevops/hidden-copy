package com.veilsun.constructkey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.BucketItem;

import java.util.UUID;

@Repository
public interface BucketItemRepository extends JpaRepository<BucketItem, UUID> {

	
}