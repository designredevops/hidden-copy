package com.veilsun.constructkey.specification.sequence;

import com.veilsun.constructkey.domain.Sequence;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "pullPlanTarget.id", pathVars = "pptId", spec = Equal.class)
public interface SequencesByPullPlanTargetIdSpec extends Specification<Sequence> {
}
