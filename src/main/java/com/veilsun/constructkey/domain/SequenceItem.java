package com.veilsun.constructkey.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ppt_sequence_item")
@Getter
@Setter
@AllArgsConstructor
public class SequenceItem extends Record {
	
	public SequenceItem() {
		// TODO Auto-generated constructor stub
	}

	public SequenceItem(String title) {
		this.title = title;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id"})
	private Sequence sequence;

	private String title;
	
	
	private Double ranking;
	
}
