package com.veilsun.constructkey.domain;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ppt")
@Getter
@Setter
@AllArgsConstructor
public class PullPlanTarget extends Record {
	public enum PullPlanTargetStatus{
		Active, Inactive
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id", "name"})
	private Project project;

	@NotBlank(message = "Name must not be blank")
	private String name;

	@NotBlank
	private String description;

	@NotBlank
	private Integer duration;
	private LocalDate completionDate;

	@Enumerated(EnumType.STRING)
	private PullPlanTargetStatus status;

	@OneToMany(mappedBy = "pullPlanTarget", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({"id"})
	private Set<PullPlanTargetMeeting> meetings;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({"id"})
	private Bucket documents;
	
	public PullPlanTarget() {
		
	}

	public PullPlanTarget(UUID pptId){
		this.setId(pptId);
	}
}
