package com.gabriel_sousa.crud_tarefas.validation;

import com.gabriel_sousa.crud_tarefas.entity.TaskEntity;
import com.gabriel_sousa.crud_tarefas.entity.TaskTypeEntity;
import com.gabriel_sousa.crud_tarefas.entity.UserEntity;
import com.gabriel_sousa.crud_tarefas.repository.TaskRepository;
import com.gabriel_sousa.crud_tarefas.repository.TaskTypeRepository;
import com.gabriel_sousa.crud_tarefas.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnershipValidator {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskTypeRepository taskTypeRepository;

    public boolean userIsTaskOwner(Long userId, Long taskId){
        try {
            UserEntity user = userRepository.getReferenceById(userId);
            TaskEntity task = taskRepository.getReferenceById(taskId);

            return task.getUser().equals(user);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Tarefa não encontrada");
        }
    }

    public boolean userIsTaskTypeOwner(Long userId, Long taskTypeId){
        try {
            UserEntity user = userRepository.getReferenceById(userId);
            TaskTypeEntity taskType = taskTypeRepository.getReferenceById(taskTypeId);

            return taskType.getUser().equals(user);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Tipo de tarefa não encontrado");
        }
    }
}
