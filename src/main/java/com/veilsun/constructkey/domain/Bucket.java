package com.veilsun.constructkey.domain;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "bucket")
@Getter
@Setter
public class Bucket extends Record {

	public Bucket(UUID bucketId) {
		this.setId(bucketId);
	}

	public Bucket(){}

	@OneToMany(mappedBy = "bucket", fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id", "name"})
	private Set<BucketItem> items;
}
