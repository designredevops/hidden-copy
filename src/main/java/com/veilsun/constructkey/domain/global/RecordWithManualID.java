package com.veilsun.constructkey.domain.global;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
}
