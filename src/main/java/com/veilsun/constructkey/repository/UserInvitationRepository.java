package com.veilsun.constructkey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.UserInvitation;
import com.veilsun.constructkey.domain.UserInvitation.InvitationStatus;

import java.util.UUID;

@Repository
public interface UserInvitationRepository extends JpaRepository<UserInvitation, UUID> {

	public Page<UserInvitation> getInvitationsByUserIdAndStatus(UUID userId, InvitationStatus status, Pageable page);
}
