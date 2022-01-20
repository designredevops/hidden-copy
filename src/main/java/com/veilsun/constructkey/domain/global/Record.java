package com.veilsun.constructkey.domain.global;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.User;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class Record {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;
	
	@Column(name = "created_on")
	@CreatedDate
	private LocalDateTime createdOn;
	
	@CreatedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id","firstName","lastName"})
	private User createdBy;
	
	@Column(name = "updated_on")
	@LastModifiedDate
	private LocalDateTime updatedOn;
	
	@Version
	@Column(columnDefinition = "bigint(20) default 1")
	private long version;
	
	@JsonIgnore
	public static final String XID = "id";
	
	@JsonIgnore
	public static final String XNAME = "name";
}
