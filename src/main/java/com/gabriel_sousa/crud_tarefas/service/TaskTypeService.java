package com.gabriel_sousa.crud_tarefas.service;

import com.gabriel_sousa.crud_tarefas.dto.CreateTaskTypeRequestDTO;
import com.gabriel_sousa.crud_tarefas.dto.GetTaskTypeResponseDTO;
import com.gabriel_sousa.crud_tarefas.mapper.TaskTypeMapper;
import com.gabriel_sousa.crud_tarefas.repository.TaskTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskTypeService {

    private final TaskTypeRepository taskTypeRepository;
    private final TaskTypeMapper taskTypeMapper;

    public void createTaskType(CreateTaskTypeRequestDTO dto, Long userId){
        taskTypeRepository.save(
                taskTypeMapper.CreateTaskTypeRequestDtoToTaskTypeEntity(dto, userId)
        );
    }

    public List<GetTaskTypeResponseDTO> getAllByUser(Long userId){
        return taskTypeMapper.entitiesToListGetResponseDTO(taskTypeRepository.findAllByUserId(userId));
    }
}
