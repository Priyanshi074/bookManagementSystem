package com.book.bookmanagement.repository;

import com.book.bookmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    // For login: find by username or email
    Optional<User> findByUsernameOrEmail(String username, String email);
}
