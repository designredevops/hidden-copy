package com.veilsun.constructkey.domain;

import javax.persistence.Entity;

import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "display_style")
@Getter
@Setter
public class DisplayStyle extends Record {
	
	private String primaryColor;
	private String secondaryColor;
	
}
