package com.veilsun.constructkey.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "user_invitation")
@Getter
@Setter
public class UserInvitation extends Record {
	
	public enum InvitationStatus {
		NEW, ACCEPTED, DELETED
	}

	@OneToMany(fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Organization organization;
	
	private LocalDateTime acceptanceDate;
	
	@Enumerated(EnumType.STRING)
	private InvitationStatus status;
}
