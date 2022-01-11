package com.veilsun.constructkey.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ppt_meeting")
@Getter
@Setter
@AllArgsConstructor
public class PullPlanTargetMeeting extends Record {

	public PullPlanTargetMeeting() {
		// TODO Auto-generated constructor stub
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id", "name"})
	private PullPlanTarget pullPlanTarget;

	@NotBlank
	private String title;
	
	@NotBlank
	private String location;

	private LocalDateTime starts;
	private LocalDateTime ends;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Team invitees;
}
