package com.gabriel_sousa.crud_tarefas.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record GlobalExceptionResponseDTO(
        HttpStatus status,
        String message,
        LocalDateTime timestamp
) {
}
