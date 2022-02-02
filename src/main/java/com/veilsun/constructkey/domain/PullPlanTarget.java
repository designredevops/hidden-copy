package com.veilsun.constructkey.domain;

import java.time.LocalDate;
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

@Entity(name = "ppt")
@Getter
@Setter
@AllArgsConstructor
public class PullPlanTarget extends Record {
	public enum PullPlanTargetStatus{
		Active, Inactive
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({Record.XID, Record.XNAME})
	private Project project;

	@NotBlank(message = "Name must not be blank")
	private String name;


	private String description;


	private Integer duration;
	private LocalDate completionDate;

	@Enumerated(EnumType.STRING)
	private PullPlanTargetStatus status;

	@OneToMany(mappedBy = "pullPlanTarget", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({"id"})
	private Set<PullPlanTargetMeeting> meetings;
	
	@OneToMany(mappedBy = "pullPlanTarget", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({"id"})
	private Set<Sequence> sequences;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({"id"})
	private Sequence weekDaySequence;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({"id"})
	private Bucket documents;
	
	public PullPlanTarget() {
		
	}

	public PullPlanTarget(UUID pptId){
		this.setId(pptId);
	}
}
