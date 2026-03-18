package com.rafael.task_manager.service;

import com.rafael.task_manager.domain.Credential;
import com.rafael.task_manager.repository.CredentialRepository;
import com.rafael.task_manager.shared.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCredentialService {
    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Credential execute(String email, String password){
        Credential credentialByEmail = this.credentialRepository.findByEmail(email);

        if(credentialByEmail != null){
            throw new RuntimeException("Email already registred");
        }

        String hashedPassword = passwordEncoder.encode(password);
        Credential credential = new Credential(email, hashedPassword, RoleEnum.USER);
        return this.credentialRepository.save(credential);
    };
}
