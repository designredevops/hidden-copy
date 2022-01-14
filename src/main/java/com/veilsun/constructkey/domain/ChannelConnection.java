package com.veilsun.constructkey.domain;

import java.util.UUID;

import javax.persistence.Entity;

import com.veilsun.constructkey.domain.global.SimpleRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "channel_connection")
@Getter
@Setter
@AllArgsConstructor
public class ChannelConnection extends SimpleRecord {

	private UUID channel;
	private String connectionId;

}
