package com.veilsun.constructkey.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "project_organization")
@Getter
@Setter
public class ProjectOrganization extends Record {

	@ManyToOne(fetch = FetchType.LAZY)
	private Project project;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Organization organization;
	
	@OneToOne(fetch = FetchType.LAZY)
	private DisplayStyle displayStyle;
	
	@OneToOne(fetch = FetchType.LAZY)
	private WorkSchedule workSchedule;
}
