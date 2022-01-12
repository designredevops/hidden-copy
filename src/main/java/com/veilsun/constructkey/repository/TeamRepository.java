package com.veilsun.constructkey.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {

	@Query("SELECT o.memberTeam.id as memberTeamId FROM project p JOIN organization o ON o.id = p.organization.id WHERE p.memberTeam.id = :memberTeamId")
	Optional<UUID> getOrganizationTeamsIdByProjectMemberTeamId(@Param("memberTeamId") UUID memberTeamId);

	@Query("SELECT p.memberTeam.id as memberTeamId FROM project p JOIN ppt AS ppt ON ppt.project.id = p.id JOIN ppt_meeting pptm ON pptm.pullPlanTarget.id = ppt.id WHERE pptm.invitees.id = :teamId")
	Optional<UUID> getProjectTeamsIdByPPTMeetingTeamId(@Param("teamId") UUID teamId);
	
	public interface TeamIds {
		UUID getMemberTeamId();
	}
}
