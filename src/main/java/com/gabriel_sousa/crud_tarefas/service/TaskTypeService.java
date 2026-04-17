package com.gabriel_sousa.crud_tarefas.service;

import com.gabriel_sousa.crud_tarefas.dto.CreateTaskTypeRequestDTO;
import com.gabriel_sousa.crud_tarefas.dto.GetTaskTypeResponseDTO;
import com.gabriel_sousa.crud_tarefas.dto.UpdateTaskTypeRequestDTO;
import com.gabriel_sousa.crud_tarefas.entity.TaskTypeEntity;
import com.gabriel_sousa.crud_tarefas.mapper.TaskTypeMapper;
import com.gabriel_sousa.crud_tarefas.repository.TaskTypeRepository;
import com.gabriel_sousa.crud_tarefas.validation.OwnershipValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskTypeService {

    private final TaskTypeRepository taskTypeRepository;
    private final TaskTypeMapper taskTypeMapper;
    private final OwnershipValidator ownershipValidator;

    public void createTaskType(CreateTaskTypeRequestDTO dto, Long userId){
        taskTypeRepository.save(
                taskTypeMapper.CreateTaskTypeRequestDtoToTaskTypeEntity(dto, userId)
        );
    }

    public List<GetTaskTypeResponseDTO> getAllByUser(Long userId){
        return taskTypeMapper.entitiesToListGetResponseDTO(taskTypeRepository.findAllByUserId(userId));
    }

    public GetTaskTypeResponseDTO updateTaskType(UpdateTaskTypeRequestDTO dto, Long taskTypeId, Long userId){
        TaskTypeEntity taskType = taskTypeRepository.getReferenceById(taskTypeId);
        if(!ownershipValidator.userIsTaskTypeOwner(userId, taskTypeId))
            throw new AccessDeniedException("Usuário não autorizado para esse tipo de terafa");

        taskTypeMapper.updateTaskTypeFromUpdateTaskTypeRequestDTO(dto, taskType);
        return taskTypeMapper.entityToGetResponseDTO(
                taskTypeRepository.save(taskType)
        );
    }

    public void deleteTaskTypeById(Long taskTypeId, Long userId){
        TaskTypeEntity taskType = taskTypeRepository.getReferenceById(taskTypeId);
        if(!ownershipValidator.userIsTaskTypeOwner(userId, taskTypeId))
            throw new AccessDeniedException("Usuário não autorizado para esse tipo de terafa");

        taskTypeRepository.delete(taskType);
    }
}
