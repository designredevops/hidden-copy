package com.veilsun.constructkey.repository;

import com.veilsun.constructkey.domain.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, UUID> {
    public List<TeamMember> findAllByTeamId(UUID teamId);
}
