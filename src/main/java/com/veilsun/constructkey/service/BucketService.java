package com.veilsun.constructkey.service;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.veilsun.constructkey.client.BucketS3Client;
import com.veilsun.constructkey.domain.Bucket;
import com.veilsun.constructkey.domain.BucketItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.repository.BucketItemRepository;
import com.veilsun.constructkey.repository.BucketRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

	public List<String> getFiles(UUID bucketId, Pageable page) {
		//return bucketItemRepository.findAllByBucketId(bucketId, page);
		List<String> files = new ArrayList<>();
		var objectListing = s3Client.listAllFiles();
		if (objectListing.getObjectSummaries().size() < 0) return Collections.EMPTY_LIST;
		for(S3ObjectSummary os : objectListing.getObjectSummaries()){
			files.add(os.getKey());
		}
		return files;
	}

	public BucketItem uploadFile(UUID bucketId, String fileName, MultipartFile incomingFile) {
		BucketItem fileToUpload = new BucketItem();
		fileToUpload.setBucket(new Bucket(bucketId));
		String storageId = null;
		Map<String, String> metadata = new HashMap<>();
		metadata.put("Content-Type", incomingFile.getContentType());
		metadata.put("Content-Length", String.valueOf(incomingFile.getSize()));
		metadata.put("Name-Key", fileName);
		try {
			storageId = s3Client.uploadFiles(fileName, incomingFile, Optional.of(metadata));
		} catch (Exception e) {
			e.printStackTrace();
		}
		fileToUpload.setStorageId(storageId);
		return bucketItemRepository.save(fileToUpload);

	}
}
