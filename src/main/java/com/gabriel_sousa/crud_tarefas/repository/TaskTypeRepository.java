package com.gabriel_sousa.crud_tarefas.repository;

import com.gabriel_sousa.crud_tarefas.entity.TaskTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskTypeRepository extends JpaRepository<TaskTypeEntity, Long> {

    Page<TaskTypeEntity> findAllByUserId(Long userId, Pageable pageable);

    boolean existsByIdAndUserId(Long id, Long userId);
}
