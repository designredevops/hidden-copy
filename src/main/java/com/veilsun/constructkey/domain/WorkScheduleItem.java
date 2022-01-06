package com.veilsun.constructkey.domain;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "work_schedule_item")
@Getter
@Setter
public class WorkScheduleItem extends Record {
	
	public enum WorkScheduleItemType {
		Range, Week, Day
	}
	
	public enum WorkScheduleItemStatus {
		OPEN(1), CLOSED(0);
		
		@Getter
		private final Integer value;
		
		WorkScheduleItemStatus(Integer value) {
			this.value = value;
		}
	}

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private WorkSchedule workSchedule;
	
	private String title;
	
	private LocalDate start;
	private LocalDate end;
	
	@Enumerated(EnumType.STRING)
	private WorkScheduleItemType type;
	
	private WorkScheduleItemStatus SUN, MON, TUE, WED, THU, FRI, SAT;
}
