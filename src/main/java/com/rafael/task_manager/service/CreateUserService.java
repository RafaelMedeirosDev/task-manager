package com.rafael.task_manager.service;

import com.rafael.task_manager.domain.Credential;
import com.rafael.task_manager.domain.User;
import com.rafael.task_manager.repository.UserRepository;
import com.rafael.task_manager.shared.dto.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CreateCredentialService createCredentialService;

    public User execute (CreateUserDTO data){
        Credential credential = this.createCredentialService.execute(data.email, data.password);
        User user = new User(data.name, credential.getId());

        return this.userRepository.save(user);

    };
}
