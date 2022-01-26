package com.veilsun.constructkey.specification.ppt.chutes;

import com.veilsun.constructkey.domain.Chute;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "id", pathVars = "chuteId", spec = Equal.class)
public interface PullPlanTargetChuteByPullPlanTargetChuteIdSpec extends Specification<Chute> {
}
