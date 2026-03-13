package com.rafael.task_manager.shared.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCredentialDTO {
    @NotNull
    @Email(message = "Invalid e-mail")
    public String email;
    @NotNull
    public String password;
}
