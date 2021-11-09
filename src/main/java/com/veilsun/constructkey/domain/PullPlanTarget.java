package com.veilsun.constructkey.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.veilsun.constructkey.domain.global.Record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ppt")
@Getter
@Setter
@AllArgsConstructor
public class PullPlanTarget extends Record {

	@ManyToOne(fetch = FetchType.LAZY)
	private Project project;
	
	private String name;
	private String description;
	private Integer duration;
	private LocalDate completionDate;
	
	@OneToMany(mappedBy = "pullPlanTarget", fetch = FetchType.LAZY)
	private Set<PullPlanTargetMeeting> meetings;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Bucket documents;
	
	public PullPlanTarget() {
		
	}
}
