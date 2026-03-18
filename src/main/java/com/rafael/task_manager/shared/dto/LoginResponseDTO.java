package com.rafael.task_manager.shared.dto;

import com.rafael.task_manager.shared.enums.RoleEnum;

public record LoginResponseDTO(String token, RoleEnum roleEnum) {
}
