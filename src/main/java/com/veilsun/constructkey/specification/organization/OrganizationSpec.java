package com.veilsun.constructkey.specification.organization;

import org.springframework.data.jpa.domain.Specification;

import com.veilsun.constructkey.domain.Organization;

import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Spec(path = "name", params = "name", spec = Like.class)
public interface OrganizationSpec extends Specification<Organization> {

}
