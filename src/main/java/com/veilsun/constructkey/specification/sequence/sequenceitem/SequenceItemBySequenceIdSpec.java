package com.veilsun.constructkey.specification.sequence.sequenceitem;

import com.veilsun.constructkey.domain.SequenceItem;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "sequence.id", pathVars = "secId", spec = Equal.class)
public interface SequenceItemBySequenceIdSpec extends Specification<SequenceItem> {
}
