package com.veilsun.constructkey.domain;

import java.util.Set;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ppt_sequence")
@Getter
@Setter
@AllArgsConstructor
public class Sequence extends Record {

	public enum SequenceType {
		Normal, Milestone, Weekday
	}

	public Sequence() {
		// TODO Auto-generated constructor stub
	}

	public Sequence(SequenceType type, PullPlanTarget ppt){
		this.setType(type);
		this.setPullPlanTarget(ppt);
	}

	public Sequence(UUID sequenceId){
		this.setId(sequenceId);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id", "name"})
	private PullPlanTarget pullPlanTarget;

	@NotBlank(message = "Name must not be null")
	private String name;
	
	@OneToMany(mappedBy = "sequence", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SequenceItem> items;

	@Enumerated(EnumType.STRING)
	private SequenceType type;
}
