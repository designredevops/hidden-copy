package com.veilsun.constructkey.service;

import com.veilsun.constructkey.domain.WorkSchedule;
import com.veilsun.constructkey.domain.WorkScheduleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.veilsun.constructkey.repository.WorkScheduleItemRepository;
import com.veilsun.constructkey.repository.WorkScheduleRepository;

import javax.transaction.Transactional;
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

	public WorkScheduleItem updateWorkScheduleItem(UUID workScheduleItemId, WorkScheduleItem item) {
		WorkScheduleItem originalWorkScheduleItem = workScheduleItemRepository.findById(workScheduleItemId).orElseThrow();
		if (item.getTitle() != null) originalWorkScheduleItem.setTitle(item.getTitle());
		if (item.getType() != null) originalWorkScheduleItem.setType(item.getType());
		if (item.getStart() != null) originalWorkScheduleItem.setStart(item.getStart());
		if (item.getEnd() != null) originalWorkScheduleItem.setEnd(item.getEnd());
		if (item.getMON() != null) originalWorkScheduleItem.setMON(item.getMON());
		if (item.getTUE() != null) originalWorkScheduleItem.setTUE(item.getTUE());
		if (item.getWED() != null) originalWorkScheduleItem.setWED(item.getWED());
		if (item.getTHU() != null) originalWorkScheduleItem.setTHU(item.getTHU());
		if (item.getFRI() != null) originalWorkScheduleItem.setFRI(item.getFRI());
		if (item.getSAT() != null) originalWorkScheduleItem.setSAT(item.getSAT());
		if (item.getSUN() != null) originalWorkScheduleItem.setSUN(item.getSUN());
		return workScheduleItemRepository.save(originalWorkScheduleItem);
	}

	public Boolean deleteWorkScheduleItem(UUID workScheduleItemId) {
		workScheduleItemRepository.deleteById(workScheduleItemId);
		return true;
	}
}
