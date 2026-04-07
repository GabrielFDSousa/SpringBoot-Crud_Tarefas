package com.gabriel_sousa.crud_tarefas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateTaskRequestDTO(
        @NotNull
        @Size(max = 160)
        String title,

        @Size(max = 400)
        String description,

        @NotNull
        @Positive
        Long taskTypeId
) {
}
