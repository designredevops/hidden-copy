package com.veilsun.constructkey.domain.global;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class RecordWithManualID {
	
	@Column(name = "created_on")
	@CreatedDate
	private LocalDateTime createdOn;
	
	@Column(name = "updated_on")
	@LastModifiedDate
	private LocalDateTime updatedOn;
	
	@Version
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(columnDefinition = "bigint(20) default 1")
	private long version;
}
