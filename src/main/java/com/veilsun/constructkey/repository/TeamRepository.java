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

	
	@Query("SELECT o.memberTeam.id FROM organization o WHERE o.adminTeam.id = :adminTeamId")
	Optional<UUID> getOrganizationMembersTeamIdByOrganzationAdminTeamId(@Param("adminTeamId") UUID adminTeamId);
	
	@Query("SELECT o.memberTeam.id as memberTeamId, o.adminTeam.id as adminTeamId FROM project p JOIN organization o WHERE p.adminTeam.id = :adminTeamId")
	Optional<TeamIds> getOrganizationTeamsIdByProjectAdminTeamId(@Param("adminTeamId") UUID adminTeamId);
	
	@Query("SELECT o.memberTeam.id as memberTeamId, o.adminTeam.id as adminTeamId FROM project p JOIN organization o WHERE p.memberTeam.id = :memberTeamId")
	Optional<TeamIds> getOrganizationTeamsIdByProjectMemberTeamId(@Param("memberTeamId") UUID memberTeamId);

	@Query("SELECT p.memberTeam.id FROM project p WHERE p.adminTeam.id = :adminTeamId")
	Optional<UUID> getProjectMembersTeamIdByProjectAdminTeamId(@Param("adminTeamId") UUID adminTeamId);
	
	@Query("SELECT p.memberTeam.id as memberTeamId, p.adminTeam.id as adminTeamId FROM project p JOIN ppt ppt JOIN ppt_meeting pptm WHERE pptm.invitees.id = :teamId")
	Optional<TeamIds> getProjectTeamsIdByPPTMeetingTeamId(@Param("teamId") UUID teamId);
	
	public interface TeamIds {
		UUID getMemberTeamId();
		UUID getAdminTeamId();	
	}
}
