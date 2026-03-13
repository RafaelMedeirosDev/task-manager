package com.rafael.task_manager.shared.dto;

import com.rafael.task_manager.shared.enums.Role;

public record LoginResponseDTO(String token, Role role) {
}
