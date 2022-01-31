package com.veilsun.constructkey.specification.bucket;

import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import org.springframework.data.jpa.domain.Specification;

import com.veilsun.constructkey.domain.BucketItem;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
        @Spec(path = "bucket.id", pathVars = "bucketId", spec = Equal.class),
        @Spec(path = "name", params = "name", spec = Like.class)
})
public interface BucketItemSpec extends Specification<BucketItem> {

}
