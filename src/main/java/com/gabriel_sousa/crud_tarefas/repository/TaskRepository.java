package com.gabriel_sousa.crud_tarefas.repository;

import com.gabriel_sousa.crud_tarefas.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> getAllByUserId(Long userId);
}
