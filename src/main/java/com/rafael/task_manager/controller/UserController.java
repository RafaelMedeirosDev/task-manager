package com.rafael.task_manager.controller;

import com.rafael.task_manager.domain.User;
import com.rafael.task_manager.service.CreateCredentialService;
import com.rafael.task_manager.service.CreateUserService;
import com.rafael.task_manager.shared.dto.CreateUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CreateUserService createUserService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping()
    public ResponseEntity<User> create(@Validated @RequestBody CreateUserDTO body){
        User user = this.createUserService.execute(body);
        return ResponseEntity.ok(user);
    };
}
