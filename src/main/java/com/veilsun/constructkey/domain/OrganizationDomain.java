package com.veilsun.constructkey.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "organization_domain")
@Getter
@Setter
public class OrganizationDomain extends Record {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id", "name"})
	private Organization organization;

	private String domainName;
	
	private String verificationHash;
	private Boolean verified;
	private LocalDateTime verifiedDate;
}
