package com.veilsun.constructkey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.PullPlanTargetMeeting;

@Repository
public interface PullPlanTargetMeetingRepository extends JpaRepository<PullPlanTargetMeeting, String> {

}
