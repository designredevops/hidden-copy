package com.veilsun.constructkey.specification.bucket;

import org.springframework.data.jpa.domain.Specification;

import com.veilsun.constructkey.domain.Bucket;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Spec(path = "id", pathVars = "bucketId", spec = Equal.class)
public interface BucketByIdSpec extends Specification<Bucket> {

}
