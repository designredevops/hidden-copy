package com.veilsun.constructkey.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.veilsun.constructkey.domain.ChannelConnection;

public interface ChannelConnectionRepository extends JpaRepository<ChannelConnection, UUID>, EntityGraphJpaSpecificationExecutor<ChannelConnection> {
	List<ChannelConnection> findAllByChannel(UUID channel);
}