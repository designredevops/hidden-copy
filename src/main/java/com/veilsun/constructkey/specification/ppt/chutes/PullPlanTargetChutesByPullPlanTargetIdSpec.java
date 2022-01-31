package com.veilsun.constructkey.specification.ppt.chutes;

import com.veilsun.constructkey.domain.Chute;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "ppt.id", pathVars = "pptId", spec = Equal.class)
public interface PullPlanTargetChutesByPullPlanTargetIdSpec extends Specification<Chute> {
}
