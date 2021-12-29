package com.veilsun.constructkey.service;

import com.veilsun.constructkey.client.BucketS3Client;
import com.veilsun.constructkey.domain.Bucket;
import com.veilsun.constructkey.domain.BucketItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.repository.BucketItemRepository;
import com.veilsun.constructkey.repository.BucketRepository;

import java.io.File;
import java.util.UUID;

@Service
public class BucketService {


	@Autowired
	private BucketRepository bucketRepository;
	
	@Autowired
	private BucketItemRepository bucketItemRepository;

	@Autowired
	private BucketS3Client s3Client;

	public Bucket getBucket(UUID bucketId) {
		return bucketRepository.findById(bucketId).orElseThrow();
	}

	public Page<BucketItem> getFiles(UUID bucketId, Pageable page) {
		return bucketItemRepository.findAllByBucketId(bucketId, page);
	}

	public BucketItem uploadFile(UUID bucketId, File file) {
		String objName = file.getName();
		Bucket bucketName = bucketRepository.findById(bucketId).orElseThrow();
		return null;
	}
}
