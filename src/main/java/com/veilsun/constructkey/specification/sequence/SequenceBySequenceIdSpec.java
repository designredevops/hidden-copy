package com.veilsun.constructkey.specification.sequence;

import com.veilsun.constructkey.domain.Sequence;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "id", pathVars = "secId", spec = Equal.class)
public interface SequenceBySequenceIdSpec extends Specification<Sequence> {
}
