package com.veilsun.constructkey.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "work_schedule")
@Getter
@Setter
public class WorkSchedule extends Record {
	
	public enum WorkScheduleType {
		Organization, Project, ProjectOrganization
	}
	
	@OneToMany(mappedBy = "workSchedule", fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id"})
	private Set<WorkScheduleItem> items;
	
	@OneToOne(fetch = FetchType.LAZY)
	private WorkScheduleItem defaultWorkScheduleItem;
	
	@Enumerated(EnumType.STRING)
	private WorkScheduleType type;
}
