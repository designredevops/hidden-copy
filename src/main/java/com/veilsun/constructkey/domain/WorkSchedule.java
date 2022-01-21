package com.veilsun.constructkey.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

	public WorkSchedule(){
		WorkScheduleItem i = new WorkScheduleItem();
		i.setType(WorkScheduleItem.WorkScheduleItemType.Week);
		i.setWorkSchedule(this);
		this.setDefaultWorkScheduleItem(i);
		this.items = new HashSet<>();
		this.items.add(i);
	}

	public WorkSchedule(UUID workSchedule){
		this.setId(workSchedule);
	}

	@JsonIncludeProperties({"id"})
	@JsonManagedReference
	@OneToMany(mappedBy = "workSchedule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<WorkScheduleItem> items;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private WorkScheduleItem defaultWorkScheduleItem;
	
	@Enumerated(EnumType.STRING)
	private WorkScheduleType type;
}
