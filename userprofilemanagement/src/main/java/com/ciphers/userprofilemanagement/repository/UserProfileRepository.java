package com.ciphers.userprofilemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ciphers.userprofilemanagement.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	boolean existsByEmail(String email);
}


