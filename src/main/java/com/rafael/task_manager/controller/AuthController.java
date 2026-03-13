package com.rafael.task_manager.controller;

import com.rafael.task_manager.domain.Credential;
import com.rafael.task_manager.repository.CredentialRepository;
import com.rafael.task_manager.service.LoginService;
import com.rafael.task_manager.service.TokenService;
import com.rafael.task_manager.shared.dto.CreateCredentialDTO;
import com.rafael.task_manager.shared.dto.CreateUserDTO;
import com.rafael.task_manager.shared.dto.LoginRequestDTO;
import com.rafael.task_manager.shared.dto.LoginResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO body) throws Exception {
        LoginResponseDTO response = this.loginService.execute(new Credential(body.email(), body.password()));
        return ResponseEntity.ok(response);
    }
}
