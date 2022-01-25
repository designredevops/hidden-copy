package com.veilsun.constructkey.specification.ppt;

import com.veilsun.constructkey.domain.PullPlanTarget;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "id", pathVars = "pptId", spec = Equal.class)
public interface PullPlanTargetByPullPlanTargetIdSpec extends Specification<PullPlanTarget> {
}
