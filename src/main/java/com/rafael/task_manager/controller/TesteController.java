package com.rafael.task_manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TesteController {
    @GetMapping("/me")
    public String me(){
        return "User Authenticated";
    };
}
