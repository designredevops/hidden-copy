package com.veilsun.constructkey.domain;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "team")
@Getter
@Setter
public class Team extends Record {

	public enum TeamType {
		OrganizationAdmin, OrganizationMember,
		ProjectAdmin, ProjectMember,
		PullPlanTargetMeeting
	}
	
	public Team(UUID userId, TeamType type) {
		this.type = type;
		this.members = Set.of(new TeamMember(userId));
	}
	
	public Team(TeamType type) {
		this.type = type;
	}
	
	public Team() {
		
	}
	
	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<TeamMember> members;
	
	@Enumerated(EnumType.STRING)
	private TeamType type;
	
}
