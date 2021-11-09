package com.veilsun.constructkey.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.veilsun.constructkey.domain.global.Record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "card")
@Getter
@Setter
@AllArgsConstructor
public class Card extends Record {
	
	public Card() {
		// TODO Auto-generated constructor stub
	}

	public enum CardType {
		Activity, Inspection, Constrain
	}

	@ManyToOne(fetch = FetchType.LAZY)
	private Chute chute;
	
	@Enumerated(EnumType.STRING)
	private CardType type;
	
	private Integer days, people;
	
	private String promise, need;
	
	private Double ranking;
	
}
