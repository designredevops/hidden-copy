package com.veilsun.constructkey.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "project_organization")
@Getter
@Setter
public class ProjectOrganization extends Record {

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JsonIncludeProperties({"id", "name"})
	private Project project;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JsonIncludeProperties({"id", "name"})
	private Organization organization;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private DisplayStyle displayStyle;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private WorkSchedule workSchedule;
}
