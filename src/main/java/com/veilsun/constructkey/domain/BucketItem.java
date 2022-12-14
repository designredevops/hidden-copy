package com.veilsun.constructkey.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "bucket_item")
@Getter
@Setter
public class BucketItem extends Record {

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id"})
	private Bucket bucket;

	@NotBlank(message = "BucketItem must have a valid name")
	private String name;
	
	@JsonIgnore
	private String storageId;
}
