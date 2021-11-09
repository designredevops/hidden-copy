package com.veilsun.constructkey.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.UserInvitation;
import com.veilsun.constructkey.domain.UserInvitation.InvitationStatus;

@Repository
public interface UserInvitationRepository extends JpaRepository<UserInvitation, String> {

	public Page<UserInvitation> getInvitationsByUserIdAndStatus(String userId, InvitationStatus status, Pageable page);
}
