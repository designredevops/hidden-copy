package com.veilsun.constructkey.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "team_member")
@Getter
@Setter
public class TeamMember extends Record {
	
	public enum TeamMemberType {
		Internal, External
	}
	
	public enum TeamMemberStatus {
		Active, Deactive
	}
	
	public enum TeamMemberRole {
		Member, Admin
	}
	
	public TeamMember(UUID userId, Team team, TeamMemberRole role) {
		this.user = new User(userId);
		this.team = team;
		this.role = role;
	}

	public TeamMember() {
		
	}

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	private Team team;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JsonIncludeProperties({"id", "firstName", "lastName"})
	private User user;
	
	@Enumerated(EnumType.STRING)
	private TeamMemberRole role;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIncludeProperties({"id", "name"})
	private Organization organization;
	
	private TeamMemberStatus status;
	
	private TeamMemberType type;
}
