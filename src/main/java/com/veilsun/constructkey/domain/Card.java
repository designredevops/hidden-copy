package com.veilsun.constructkey.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.veilsun.constructkey.domain.global.Record;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "card")
@Getter
@Setter
public class Card extends Record {
	
	public enum CardType {
		Activity, Inspection, Constrain
	}

	@ManyToOne(fetch = FetchType.LAZY)
	private Chute chute;
	
	@Enumerated(EnumType.STRING)
	private CardType type;
	
	private Integer days, people;
	
	private String promise, need;
	
}
