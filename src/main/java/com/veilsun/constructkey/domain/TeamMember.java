package com.veilsun.constructkey.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

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
	
	public TeamMember(String userId) {
		this.user = new User(userId);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	private Team team;
	
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	private String role;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Organization organization;
	
	private TeamMemberStatus status;
	
	private TeamMemberType type;
}
