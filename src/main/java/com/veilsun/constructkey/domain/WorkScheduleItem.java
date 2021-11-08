package com.veilsun.constructkey.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "work_schedule_item")
@Getter
@Setter
public class WorkScheduleItem extends Record {
	
	public enum WorkScheduleItemStatus {
		OPEN(1), CLOSED(0);
		
		@Getter
		private final Integer value;
		
		WorkScheduleItemStatus(Integer value) {
			this.value = value;
		}
	}

	@ManyToOne(fetch = FetchType.LAZY)
	private WorkSchedule workSchedule;
	
	private LocalDate start;
	private LocalDate end;
	
	private WorkScheduleItemStatus SUN, MON, TUE, WED, THU, FRI, SAT;
}
