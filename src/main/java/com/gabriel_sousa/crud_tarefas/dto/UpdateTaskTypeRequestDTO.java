package com.gabriel_sousa.crud_tarefas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTaskTypeRequestDTO(
        @NotNull
        @Size(max = 160)
        String name
) {
}
