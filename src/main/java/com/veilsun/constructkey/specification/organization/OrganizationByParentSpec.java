package com.veilsun.constructkey.specification.organization;

import org.springframework.data.jpa.domain.Specification;

import com.veilsun.constructkey.domain.Organization;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Spec(path = "parentOrganization.id", pathVars = "orgId", spec = Equal.class)
public interface OrganizationByParentSpec extends Specification<Organization> {

}
