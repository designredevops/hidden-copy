package com.veilsun.constructkey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.repository.BucketItemRepository;
import com.veilsun.constructkey.repository.BucketRepository;

@Service
public class BucketService {


	@Autowired
	private BucketRepository bucketRepository;
	
	@Autowired
	private BucketItemRepository bucketItemRepository;

	
	

}
