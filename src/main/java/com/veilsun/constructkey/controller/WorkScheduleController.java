package com.veilsun.constructkey.controller;

import java.time.LocalDate;

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
import org.springframework.web.bind.annotation.RestController;

import com.veilsun.constructkey.domain.WorkScheduleItem;
import com.veilsun.constructkey.service.WorkScheduleService;

@RestController
@RequestMapping("/workschedule/{workScheduleId}")
public class WorkScheduleController {

	Logger logger = LoggerFactory.getLogger(WorkScheduleController.class);
	
	@Autowired
	WorkScheduleService workScheduleService;
	
	@GetMapping("")
	public ResponseEntity<?> getWorkSchedule(@PathVariable("workScheduleId") String workScheduleId) {
		return null;
	}
	
	@GetMapping("/calendar")
	public ResponseEntity<?> getWorkScheduleCalendar(
			@PathVariable("workScheduleId") String workScheduleId,
			@PathVariable("from") LocalDate from,
			@PathVariable("to") LocalDate to
			) {
		return null;
	}
	
	@PostMapping("/default/{workScheduleItemId}")
	public ResponseEntity<?> setDefaultWorkScheduleItem(
			@PathVariable("workScheduleId") String workScheduleId,
			@PathVariable("workScheduleItemId") String workScheduleItemId) {
		return null;
	}
	
	@GetMapping("/item")
	public ResponseEntity<?> getWorkScheduleItems(@PathVariable("workScheduleId") String workScheduleId) {
		return null;
	}
	
	@PostMapping("/item")
	public ResponseEntity<?> addWorkScheduleItem(
			@PathVariable("workScheduleId") String workScheduleId, 
			@RequestBody WorkScheduleItem item) {
		return null;
	}
	
	@PutMapping("/item/{workScheduleItemId}")
	public ResponseEntity<?> updateWorkScheduleItem(
			@PathVariable("workScheduleId") String workScheduleId, 
			@PathVariable("workScheduleItemId") String workScheduleItemId,
			@RequestBody WorkScheduleItem item) {
		return null;
	}
	
	@DeleteMapping("/item/{workScheduleItemId}")
	public ResponseEntity<?> deleteWorkScheduleItem(
			@PathVariable("workScheduleId") String workScheduleId, 
			@PathVariable("workScheduleItemId") String workScheduleItemId) {
		return null;
	}
}
