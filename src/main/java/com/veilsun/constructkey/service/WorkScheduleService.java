package com.veilsun.constructkey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.repository.WorkScheduleItemRepository;
import com.veilsun.constructkey.repository.WorkScheduleRepository;

@Service
public class WorkScheduleService {


	@Autowired
	private WorkScheduleRepository workScheduleRepository;
	
	@Autowired
	private WorkScheduleItemRepository workScheduleItemRepository;

	
	

}
