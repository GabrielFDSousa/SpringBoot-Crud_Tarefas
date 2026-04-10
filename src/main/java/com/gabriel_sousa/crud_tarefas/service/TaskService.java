package com.gabriel_sousa.crud_tarefas.service;

import com.gabriel_sousa.crud_tarefas.dto.CreateTaskRequestDTO;
import com.gabriel_sousa.crud_tarefas.dto.GetTaskResponseDTO;
import com.gabriel_sousa.crud_tarefas.dto.UpdateTaskRequestDTO;
import com.gabriel_sousa.crud_tarefas.entity.TaskEntity;
import com.gabriel_sousa.crud_tarefas.mapper.TaskMapper;
import com.gabriel_sousa.crud_tarefas.repository.TaskRepository;
import com.gabriel_sousa.crud_tarefas.repository.TaskTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskTypeRepository taskTypeRepository;

    public void createTask(CreateTaskRequestDTO dto, Long userId){
        boolean existsType = taskTypeRepository.existsByIdAndUserId(dto.taskTypeId(), userId);
        if(!existsType) throw new EntityNotFoundException("Tipo de tarefa não encontrado");
        taskRepository.save(
                taskMapper.createTaskRequestDtoToTaskEntity(dto, userId)
        );
    }

    public List<GetTaskResponseDTO> getAllByUser(Long userId){
        return taskMapper.entitiesToListGetResponseDto(
                taskRepository.getAllByUserId(userId)
        );
    }

    public void deleteTaskById(Long taskId, Long userId){
        TaskEntity task = taskRepository.getReferenceById(taskId);
        if(!task.getUser().getId().equals(userId)) throw new AccessDeniedException("Usuário não autorizado");

        taskRepository.delete(task);
    }

    public GetTaskResponseDTO updateTask(UpdateTaskRequestDTO dto, Long taskId, Long userId){
        TaskEntity task = taskRepository.getReferenceById(taskId);
        if(!task.getUser().getId().equals(userId)) throw new AccessDeniedException("Usuário não autorizado");

        taskMapper.updateTaskFromUpdateTaskRequestDTO(dto, task);
        return taskMapper.entityToGetResponseDto(
                taskRepository.save(task)
        );
    }

    public GetTaskResponseDTO concludeTask(Long taskId, Long userId){
        TaskEntity task = taskRepository.getReferenceById(taskId);
        if(!task.getUser().getId().equals(userId)) throw new AccessDeniedException("Usuário não autorizado");

        task.setCompleted(true);
        task.setCompleted_at(LocalDateTime.now());
        return taskMapper.entityToGetResponseDto(
                taskRepository.save(task)
        );
    }
}
