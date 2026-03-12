package com.rafael.task_manager.shared.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDTO {
    @NotBlank(message = "name is not optional")
    @Size(min = 2)
    public String name;
    @NotBlank(message = "Email is not optional")
    @Email(message = "Invalid email address")
    public String email;
    @NotBlank(message = "Password is not optional")
    @Size(min = 6, message = "The password must be at least 6 characters long")
    public String password;
}
