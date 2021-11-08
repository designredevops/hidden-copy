package com.veilsun.constructkey.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.veilsun.constructkey.domain.global.Record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
public class User extends Record {

	public User(String userId) {
		this.setId(UUID.fromString(userId));
	}

	public User() {
		
	}

	private String firstName;
	private String lastName;
	
	@JsonIgnore
	private String email;
	
	@JsonIgnore
	private Boolean emailVerified;
	
	@JsonIgnore
	private LocalDateTime emailVerifiedDate;
	
	@JsonIgnore
	private String mobile;
	
	@JsonIgnore
	private Boolean mobileVerified;
	
	@JsonIgnore
	private LocalDateTime mobileVerifiedDate;
}
