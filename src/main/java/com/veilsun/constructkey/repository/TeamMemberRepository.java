package com.veilsun.constructkey.repository;

import com.veilsun.constructkey.domain.TeamMember;
import com.veilsun.constructkey.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, UUID> {
    public List<TeamMember> findAllByTeamId(UUID teamId);

    public TeamMember findOneByTeamIdAndId(UUID teamId, UUID id);

    @Query("SELECT tm.user FROM team_member tm WHERE tm.team.id = :teamId")
    public Page<List<User>> findAllUsersByTeamId(@Param("teamId") UUID teamId, Pageable page);
}
