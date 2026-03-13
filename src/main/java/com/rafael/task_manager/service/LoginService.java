package com.rafael.task_manager.service;

import com.rafael.task_manager.domain.Credential;
import com.rafael.task_manager.domain.User;
import com.rafael.task_manager.repository.CredentialRepository;
import com.rafael.task_manager.repository.UserRepository;
import com.rafael.task_manager.shared.dto.LoginResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    public LoginResponseDTO execute(Credential data) throws Exception{
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Credential credentialFromDb = this.credentialRepository.findByEmail(data.getEmail());

        Optional<User> user = this.userRepository.getByCredentialId(credentialFromDb.getId());
        if(user.isEmpty()){
            throw new EntityNotFoundException("not found user");
        }
        String token = this.tokenService.generateToken((Credential) auth.getPrincipal(), user.get().getId());
        return new LoginResponseDTO(token, credentialFromDb.getRole());

    }

}
