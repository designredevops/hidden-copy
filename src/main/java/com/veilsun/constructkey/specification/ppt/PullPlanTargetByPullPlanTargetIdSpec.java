package com.veilsun.constructkey.specification.ppt;

import com.veilsun.constructkey.domain.PullPlanTarget;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "id", pathVars = "pptId", spec = Equal.class),
        @Spec(path = "name", params = "name", spec = Like.class)
})
public interface PullPlanTargetByPullPlanTargetIdSpec extends Specification<PullPlanTarget> {
}
