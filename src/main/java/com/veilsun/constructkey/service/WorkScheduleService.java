package com.veilsun.constructkey.service;

import com.veilsun.constructkey.domain.WorkSchedule;
import com.veilsun.constructkey.domain.WorkScheduleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.repository.WorkScheduleItemRepository;
import com.veilsun.constructkey.repository.WorkScheduleRepository;

import java.util.UUID;

@Service
public class WorkScheduleService {


	@Autowired
	private WorkScheduleRepository workScheduleRepository;
	
	@Autowired
	private WorkScheduleItemRepository workScheduleItemRepository;


	public WorkSchedule getWorkSchedule(UUID workScheduleId) {
		return workScheduleRepository.findById(workScheduleId).orElseThrow();
	}

	public Page<WorkScheduleItem> getWorkScheduleItems(UUID workScheduleId, Pageable page) {
		return workScheduleItemRepository.findAllByWorkScheduleId(workScheduleId, page);
	}

	public WorkScheduleItem addWorkScheduleItem(UUID workScheduleId, WorkScheduleItem item) {
		item.setWorkSchedule(new WorkSchedule(workScheduleId));
		return workScheduleItemRepository.save(item);
	}

	public WorkScheduleItem updateWorkScheduleItem(WorkScheduleItem item) {
		return workScheduleItemRepository.save(item);
	}

	public Boolean deleteWorkScheduleItem(UUID workScheduleItemId) {
		workScheduleItemRepository.deleteById(workScheduleItemId);
		return true;
	}
}
