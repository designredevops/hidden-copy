package com.veilsun.constructkey.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.veilsun.constructkey.domain.global.Record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "bucket_item")
@Getter
@Setter
public class BucketItem extends Record {

	@ManyToOne(fetch = FetchType.LAZY)
	private Bucket bucket;
	
	private String name;
	
	@JsonIgnore
	private String storageId;
}
