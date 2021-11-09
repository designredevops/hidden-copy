package com.veilsun.constructkey.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "work_schedule")
@Getter
@Setter
public class WorkSchedule extends Record {
	
	@OneToMany(mappedBy = "workSchedule", fetch = FetchType.LAZY)
	private Set<WorkScheduleItem> items;
	
	@OneToOne(fetch = FetchType.LAZY)
	private WorkScheduleItem defaultWorkScheduleItem;
}
