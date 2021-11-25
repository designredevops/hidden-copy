package com.veilsun.constructkey.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.veilsun.constructkey.domain.global.RecordWithManualID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
public class User extends RecordWithManualID {

	public User(UUID userId) {
		this.setId(userId);
	}

	public User() {
		
	}
	
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;

	private String firstName;
	private String lastName;
	private String email;
	private Boolean emailVerified;
	private LocalDateTime emailVerifiedDate;
	private String mobile;
	private Boolean mobileVerified;
	private LocalDateTime mobileVerifiedDate;
}
