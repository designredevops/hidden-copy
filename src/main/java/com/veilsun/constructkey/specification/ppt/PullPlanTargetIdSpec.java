package com.veilsun.constructkey.specification.ppt;

import org.springframework.data.jpa.domain.Specification;

import com.veilsun.constructkey.domain.PullPlanTarget;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
	@Spec(path = "id", pathVars = "pptId", spec = Equal.class),
	@Spec(path = "project.id", pathVars = "projectId", spec = Equal.class)
	
})
public interface PullPlanTargetIdSpec extends Specification<PullPlanTarget> {

}
