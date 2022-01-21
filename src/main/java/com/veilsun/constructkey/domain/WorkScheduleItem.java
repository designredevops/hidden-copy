package com.veilsun.constructkey.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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

	@JsonIncludeProperties({"id", "type"})
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private WorkSchedule workSchedule;
	
	private String title;
	
	private LocalDate start;
	private LocalDate end;
	
	@Enumerated(EnumType.STRING)
	private WorkScheduleItemType type;
	
	private WorkScheduleItemStatus SUN, MON, TUE, WED, THU, FRI, SAT;
}
