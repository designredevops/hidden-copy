package com.veilsun.constructkey.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "work_schedule")
@Getter
@Setter
public class WorkSchedule extends Record {
	
	@OneToMany(mappedBy = "workSchedule", fetch = FetchType.LAZY)
	private Set<WorkScheduleItem> items;
	
	private WorkScheduleItem defaultWorkScheduleItem;
}
