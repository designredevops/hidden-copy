package com.veilsun.constructkey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.PullPlanTargetMeeting;

import java.util.UUID;

@Repository
public interface PullPlanTargetMeetingRepository extends JpaRepository<PullPlanTargetMeeting, UUID> {

    Page<PullPlanTargetMeeting> findAllByPullPlanTargetId(UUID pptId, Pageable page);
}
