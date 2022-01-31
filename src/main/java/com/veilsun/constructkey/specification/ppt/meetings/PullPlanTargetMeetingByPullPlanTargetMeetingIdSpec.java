package com.veilsun.constructkey.specification.ppt.meetings;

import com.veilsun.constructkey.domain.PullPlanTargetMeeting;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "id", pathVars = "meetingId", spec = Equal.class)
public interface PullPlanTargetMeetingByPullPlanTargetMeetingIdSpec extends Specification<PullPlanTargetMeeting> {
}
