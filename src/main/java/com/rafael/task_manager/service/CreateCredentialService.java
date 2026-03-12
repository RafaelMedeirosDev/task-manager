package com.rafael.task_manager.service;

import com.rafael.task_manager.domain.Credential;
import com.rafael.task_manager.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCredentialService {
    @Autowired
    private CredentialRepository credentialRepository;

    public Credential execute(String email, String password){
        Credential credentialByEmail = this.credentialRepository.findByEmail(email);

        if(credentialByEmail != null){
            throw new RuntimeException("Email already registred");
        }

        String hashedPassword = new BCryptPasswordEncoder().encode(password);
        Credential credential = new Credential(email, hashedPassword);
        return this.credentialRepository.save(credential);
    };
}
