package com.veilsun.constructkey.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "project")
@Getter
@Setter
@AllArgsConstructor
public class Project extends Record {
	
	public enum ProjectStatus{
		Completed, Inactive,
		Active, Planning
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id","name"})
	private Organization organization;

	@NotBlank
	private String name;
	private String number;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private String address;

	@Enumerated(EnumType.STRING)
	private ProjectStatus projectStatus;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({"id","members", "type"})
	private Team memberTeam;

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ProjectLocation> locations;

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ProjectOrganization> tradePartners;

	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({"id","name"})
	private Set<PullPlanTarget> pullPlanTargets;

	private Boolean allowExternalInvites;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private WorkSchedule workSchedule;
	
	private Boolean archived;
	private LocalDateTime archiveDate;
	
	public Project() {

	}

	public Project(UUID projectId){
		this.setId(projectId);
	}
}
