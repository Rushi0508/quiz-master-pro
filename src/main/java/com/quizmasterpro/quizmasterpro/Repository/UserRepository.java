package com.quizmasterpro.quizmasterpro.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizmasterpro.quizmasterpro.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
