package com.veilsun.constructkey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

	
}
