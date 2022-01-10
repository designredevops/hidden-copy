package com.veilsun.constructkey.domain;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.jdbc.Work;

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
