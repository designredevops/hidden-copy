package com.veilsun.constructkey.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "organization")
@Getter
@Setter
public class Organization extends Record {
	
	public enum OrganizationType {
		GeneralContractor, TradePartner, SubOrganization
	}

	private String name;
	
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private OrganizationType type;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private Team adminTeam;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private Team memberTeam;
	
	@JsonIgnore
	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	private Set<Project> projects;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private Organization parentOrganization;
	
	@JsonIgnore
	private Boolean allowExternalInvites;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	private DisplayStyle displayStyle;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private WorkSchedule workSchedule;
}
