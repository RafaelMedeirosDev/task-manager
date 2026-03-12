package com.rafael.task_manager.repository;

import com.rafael.task_manager.domain.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, String> {
    Credential findByEmail(String email);
}
