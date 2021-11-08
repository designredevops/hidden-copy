package com.veilsun.constructkey.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "project_location")
@Getter
@Setter
public class ProjectLocation extends Record {

	@ManyToOne(fetch = FetchType.LAZY)
	private Project project;
	
	private String name;
}
