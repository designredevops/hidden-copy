package com.veilsun.constructkey.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.UUID;

@RestController
@RequestMapping("/bucket/{bucketId}")
public class BucketController {

	Logger logger = LoggerFactory.getLogger(BucketController.class);
	
	@Autowired
	BucketService bucketService;
	
	@GetMapping("")
	public ResponseEntity<?> getBucket(@PathVariable("bucketId") UUID bucketId) {
		return null;
	}
	
	@GetMapping("/file")
	public ResponseEntity<?> getFiles(@PathVariable("bucketId") UUID bucketId) {
		return null;
	}
	
	@PostMapping("/file")
	public ResponseEntity<?> addFile(@PathVariable("teamId") UUID teamId, @RequestBody BucketItem file) {
		return null;
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
