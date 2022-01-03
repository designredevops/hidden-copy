package com.veilsun.constructkey.controller;

import com.veilsun.constructkey.domain.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.BucketItem;
import com.veilsun.constructkey.service.BucketService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bucket/{bucketId}")
public class BucketController {

	Logger logger = LoggerFactory.getLogger(BucketController.class);
	
	@Autowired
	BucketService bucketService;
	
	@GetMapping("")
	public ResponseEntity<?> getBucket(@PathVariable("bucketId") UUID bucketId) {
		return new ResponseEntity<Bucket>(bucketService.getBucket(bucketId), HttpStatus.OK);
	}
	
	@GetMapping("/file")
	public ResponseEntity<?> getFiles(@PathVariable("bucketId") UUID bucketId, Pageable page) {

		return new ResponseEntity<List<String>>(bucketService.getFiles(bucketId, page), HttpStatus.OK);
	}
	
	@PostMapping("/file/{fileName}")
	public ResponseEntity<?> addFile(@PathVariable("bucketId") UUID bucketId, @PathVariable("fileName") String fileName,
									 @RequestParam MultipartFile file) {

		return new ResponseEntity<BucketItem>(bucketService.uploadFile(bucketId, fileName, file), HttpStatus.OK);
	}
	
	@PutMapping("/file/{fileId}")
	public ResponseEntity<?> updateFile(
			@PathVariable("bucketId") UUID bucketId,
			@PathVariable("fileId") UUID fileId,
			@RequestBody BucketItem file) {
		return null;
	}
	
	@DeleteMapping("/file/{fileId}")
	public ResponseEntity<?> deleteFile(
			@PathVariable("bucketId") UUID bucketId,
			@PathVariable("fileId") UUID fileId) {
		return null;
	}
}
