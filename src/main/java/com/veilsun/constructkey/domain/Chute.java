package com.veilsun.constructkey.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "ppt_chute")
@Getter
@Setter
@AllArgsConstructor
public class Chute extends Record {

	public Chute() {
		// TODO Auto-generated constructor stub
	}

	public Chute(UUID chuteId){
		this.setId(chuteId);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id", "name"})
	private PullPlanTarget ppt;
	
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id", "name"})
	private Organization organization;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private DisplayStyle displayStyle;
}
