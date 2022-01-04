package com.veilsun.constructkey.service;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.veilsun.constructkey.client.BucketS3Client;
import com.veilsun.constructkey.domain.Bucket;
import com.veilsun.constructkey.domain.BucketItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

	public Page<BucketItem> getFiles(UUID bucketId, Pageable page) {
		return bucketItemRepository.findAllByBucketId(bucketId, page);
	}

	public BucketItem uploadFile(UUID bucketId, String fileName, MultipartFile incomingFile) {
		BucketItem fileToUpload = new BucketItem();
		fileToUpload.setBucket(new Bucket(bucketId));
		String storageId = null;
		Map<String, String> metadata = new HashMap<>();
		metadata.put("Content-Type", incomingFile.getContentType());
		metadata.put("Content-Length", String.valueOf(incomingFile.getSize()));
		metadata.put("Name-Key", fileName);
		BucketItem proxyBucketItem = bucketItemRepository.save(fileToUpload);
		metadata.put("Main-Id", proxyBucketItem.getId().toString());
		String reverseName = proxyBucketItem.getId() + "_" + fileName;
		fileToUpload.setName(reverseName);
		try {
			storageId = s3Client.uploadFiles(reverseName, incomingFile, Optional.of(metadata));
		} catch (Exception e) {
			e.printStackTrace();
		}
		fileToUpload.setStorageId(storageId);
		return bucketItemRepository.save(fileToUpload);

	}

	public BucketItem updateFile(UUID bucketId, UUID fileId, MultipartFile file) {
		return uploadFile(bucketId, file.getName(), file);
	}

	public Boolean deleteFile(UUID bucketId, UUID fileName) {
		s3Client.deleteFile(fileName.toString());
		return true;
	}
}
