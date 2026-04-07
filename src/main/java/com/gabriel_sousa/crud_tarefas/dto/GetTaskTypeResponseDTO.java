package com.gabriel_sousa.crud_tarefas.dto;

import java.time.LocalDateTime;

public record GetTaskTypeResponseDTO(
        Long id,
        String name,
        Long userId,
        LocalDateTime created_at
) {
}
