package com.veilsun.constructkey.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.BucketItem;

@Repository
public interface BucketItemRepository extends JpaRepository<BucketItem, UUID>, EntityGraphJpaSpecificationExecutor<BucketItem> {


    Page<BucketItem> findAllByBucketId(UUID bucketId, Pageable page);
}
