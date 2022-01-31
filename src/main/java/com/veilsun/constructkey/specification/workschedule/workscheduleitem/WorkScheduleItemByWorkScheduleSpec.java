package com.veilsun.constructkey.specification.workschedule.workscheduleitem;

import com.veilsun.constructkey.domain.WorkScheduleItem;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "workSchedule.id", pathVars = "workScheduleId", spec = Equal.class)
public interface WorkScheduleItemByWorkScheduleSpec extends Specification<WorkScheduleItem> {
}
