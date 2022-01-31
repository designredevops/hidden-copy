package com.veilsun.constructkey.specification.ppt.meetings;

import com.veilsun.constructkey.domain.PullPlanTargetMeeting;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;


@Spec(path = "pullPlanTarget.id", pathVars = "pptId", spec = Equal.class)
public interface PullPlanTargetMeetingsByPullPlanTargetIdSpec extends Specification<PullPlanTargetMeeting> {
}
