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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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

	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@Enumerated(EnumType.STRING)
	private OrganizationType type;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({"id"})
	private Team adminTeam;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({"id"})
	private Team memberTeam;
	
	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id", "name"})
	private Set<Project> projects;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id", "name"})
	private Organization parentOrganization;
	
	private Boolean allowExternalInvites;
	
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private DisplayStyle displayStyle;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIncludeProperties({"id"})
	private WorkSchedule workSchedule;
}
