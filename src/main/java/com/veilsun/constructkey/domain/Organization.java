package com.veilsun.constructkey.domain;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

	public Organization(){}

	public Organization(UUID orgId){
		this.setId(orgId);
	}

	private String name;
	
	@Enumerated(EnumType.STRING)
	private OrganizationType type;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Team adminTeam;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Team memberTeam;
	
	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	private Set<Project> projects;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Organization parentOrganization;
	
	private Boolean allowExternalInvites;
	
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private DisplayStyle displayStyle;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private WorkSchedule workSchedule;
}
