package com.veilsun.constructkey.specification.workschedule;

import org.springframework.data.jpa.domain.Specification;

import com.veilsun.constructkey.domain.WorkSchedule;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Spec(path = "id", pathVars = "workScheduleId", spec = Equal.class)
public interface WorkScheduleIdSpec extends Specification<WorkSchedule> {
}
