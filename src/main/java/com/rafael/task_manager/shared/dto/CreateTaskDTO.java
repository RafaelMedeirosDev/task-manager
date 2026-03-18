package com.rafael.task_manager.shared.dto;

import com.rafael.task_manager.shared.enums.PriorityTypeEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateTaskDTO(
        @NotBlank(message = "Title is not oprional")
        @Size(max = 100, message = "The title should have a maximum of 100 characters.")
        String title,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(max = 500, message = "The description should have a maximum of 500 characters.")
        String description,

        @NotNull(message = "Priority is noy optional")
        PriorityTypeEnum priority,

        @NotNull(message = "Deadline is not optional")
        @FutureOrPresent(message = "Deadline cannot be in the past")
        LocalDate deadline

) {
}
