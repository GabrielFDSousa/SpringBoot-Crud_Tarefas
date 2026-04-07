package com.gabriel_sousa.crud_tarefas.dto;

import java.time.LocalDateTime;

public record GetTaskResponseDTO(
        Long id,
        String title,
        String description,
        boolean completed,
        Long userId,
        Long taskTypeId,
        LocalDateTime created_at,
        LocalDateTime completed_at
) {
}
