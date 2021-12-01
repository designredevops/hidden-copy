package com.veilsun.constructkey.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import com.veilsun.constructkey.domain.global.Record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "project")
@Getter
@Setter
@AllArgsConstructor
public class Project extends Record {

	@ManyToOne(fetch = FetchType.LAZY)
	private Organization organization;

	private String name;
	private String number;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Team adminTeam;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Team memberTeam;

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<ProjectLocation> locations;

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<ProjectOrganization> tradePartners;

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<PullPlanTarget> pullPlanTargets;

	private Boolean allowExternalInvites;

	@OneToOne(fetch = FetchType.LAZY)
	private WorkSchedule workSchedule;
	
	private Boolean archived;
	private LocalDateTime archiveDate;
	
	public Project() {

	}

}
