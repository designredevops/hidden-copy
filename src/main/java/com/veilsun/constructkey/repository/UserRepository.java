package com.veilsun.constructkey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veilsun.constructkey.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> getUserById(String userId);
	Optional<User> findOneByEmailOrMobile(String email, String mobile);
}
