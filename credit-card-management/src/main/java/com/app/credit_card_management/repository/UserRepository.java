// File: UserRepository.java
package com.app.credit_card_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.credit_card_management.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
