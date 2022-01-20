package com.veilsun.constructkey.controller;

import java.net.URL;
import java.util.UUID;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.veilsun.constructkey.domain.Bucket;
import com.veilsun.constructkey.domain.BucketItem;
import com.veilsun.constructkey.service.BucketService;
import com.veilsun.constructkey.specification.bucket.BucketByIdSpec;

@RestController
@RequestMapping("/bucket/{bucketId}")
public class BucketController {

	Logger logger = LoggerFactory.getLogger(BucketController.class);
	
	@Autowired
	BucketService bucketService;
	
	@GetMapping("")
	public ResponseEntity<?> getBucket(
			@PathVariable("bucketId") UUID bucketId,
			BucketByIdSpec spec,
			@RequestParam(name = "paths", required = false) String... paths) {
		return new ResponseEntity<Bucket>(bucketService.getBucket(spec, paths), HttpStatus.OK);
	}
	
	@GetMapping("/file")
	public ResponseEntity<?> getFiles(@PathVariable("bucketId") UUID bucketId, Pageable page) {

		return new ResponseEntity<Page<BucketItem>>(bucketService.getFiles(bucketId, page), HttpStatus.OK);
	}

	@PostMapping("/file")
	public ResponseEntity<?> addFile(@PathVariable("bucketId") UUID bucketId, @Valid @RequestParam("fileName") String fileName,
									 @RequestParam MultipartFile file) {
		return new ResponseEntity<BucketItem>(bucketService.uploadFile(bucketId, fileName, file), HttpStatus.OK);
	}
	
	@PutMapping("/file/{fileId}")
	public ResponseEntity<?> updateFile(
			@PathVariable("bucketId") UUID bucketId,
			@PathVariable("fileId") UUID fileId,
			@RequestParam MultipartFile file) {
		return new ResponseEntity<BucketItem>(bucketService.updateFile(bucketId, fileId, file), HttpStatus.OK);
	}

	@GetMapping("/file/{fileId}/download")
	public ResponseEntity<?> downloadFile(@PathVariable("fileId") UUID fileId){
		return new ResponseEntity<URL>(bucketService.downloadFile(fileId), HttpStatus.OK);
	}

	@DeleteMapping("/file/{fileId}")
	public ResponseEntity<?> deleteFile(
			@PathVariable("bucketId") UUID bucketId,
			@PathVariable("fileId") UUID fileId) {
		return new ResponseEntity<Boolean>(bucketService.deleteFile(bucketId, fileId), HttpStatus.OK);
	}
}
