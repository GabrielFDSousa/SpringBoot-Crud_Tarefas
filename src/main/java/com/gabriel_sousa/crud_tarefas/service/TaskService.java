package com.gabriel_sousa.crud_tarefas.service;

import com.gabriel_sousa.crud_tarefas.dto.CreateTaskRequestDTO;
import com.gabriel_sousa.crud_tarefas.dto.GetTaskResponseDTO;
import com.gabriel_sousa.crud_tarefas.mapper.TaskMapper;
import com.gabriel_sousa.crud_tarefas.repository.TaskRepository;
import com.gabriel_sousa.crud_tarefas.repository.TaskTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskTypeRepository taskTypeRepository;

    public void createTask(CreateTaskRequestDTO dto, Long userId){
        boolean existsType = taskTypeRepository.existsById(dto.taskTypeId());
        if(!existsType) throw new EntityNotFoundException("Tipo de tarefa não existente");
        taskRepository.save(
                taskMapper.createTaskRequestDtoToTaskEntity(dto, userId)
        );
    }

    public List<GetTaskResponseDTO> getAllByUser(Long userId){
        return taskMapper.entitiesToListGetResponseDto(
                taskRepository.getAllByUserId(userId)
        );
    }

}
