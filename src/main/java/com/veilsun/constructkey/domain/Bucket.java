package com.veilsun.constructkey.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "bucket")
@Getter
@Setter
public class Bucket extends Record {
	
	@OneToMany(mappedBy = "bucket", fetch = FetchType.LAZY)
	private Set<BucketItem> items;
}
