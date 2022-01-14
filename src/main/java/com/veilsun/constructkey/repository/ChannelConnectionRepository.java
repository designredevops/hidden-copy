package com.veilsun.constructkey.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veilsun.constructkey.domain.ChannelConnection;

public interface ChannelConnectionRepository extends JpaRepository<ChannelConnection, UUID> {
	List<ChannelConnection> findAllByChannel(UUID channel);
}