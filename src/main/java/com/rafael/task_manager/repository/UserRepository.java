package com.rafael.task_manager.repository;

import com.rafael.task_manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getByCredentialId(UUID credentialId);

}
