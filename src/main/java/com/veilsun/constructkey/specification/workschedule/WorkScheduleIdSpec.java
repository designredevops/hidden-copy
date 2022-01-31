package com.veilsun.constructkey.specification.workschedule;

import com.veilsun.constructkey.domain.WorkSchedule;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "id", pathVars = "workScheduleId", spec = Equal.class)
public interface WorkScheduleIdSpec extends Specification<WorkSchedule> {
}
