package com.veilsun.constructkey.specification.ppt;

import org.springframework.data.jpa.domain.Specification;

import com.veilsun.constructkey.domain.PullPlanTarget;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Join(path = "project", alias = "p")
@Spec(path = "p.organization.id", pathVars = "orgId", spec = Equal.class)
public interface PullPlanTargetByOrganizationSpec extends Specification<PullPlanTarget> {

}
