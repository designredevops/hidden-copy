package com.veilsun.constructkey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.Bucket;

import java.util.UUID;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, UUID> {

	
}
