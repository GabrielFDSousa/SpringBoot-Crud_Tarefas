package com.gabriel_sousa.crud_tarefas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignUpRequestDTO(
        @NotNull
        @Size(max = 160)
        String name,

        @NotNull
        @Email
        @Size(max = 255)
        String email,

        @NotNull
        @Size(max = 255)
        String password
) {
}
