package com.gabriel_sousa.crud_tarefas.mapper;

import com.gabriel_sousa.crud_tarefas.dto.CreateTaskRequestDTO;
import com.gabriel_sousa.crud_tarefas.dto.GetTaskResponseDTO;
import com.gabriel_sousa.crud_tarefas.dto.UpdateTaskRequestDTO;
import com.gabriel_sousa.crud_tarefas.entity.TaskEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completed", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "completed_at", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "taskType.id", source = "dto.taskTypeId")
    TaskEntity createTaskRequestDtoToTaskEntity(CreateTaskRequestDTO dto, Long userId);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "taskType.id", target = "taskTypeId")
    GetTaskResponseDTO entityToGetResponseDto(TaskEntity entity);

    List<GetTaskResponseDTO> entitiesToListGetResponseDto(List<TaskEntity> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void updateTaskFromUpdateTaskRequestDTO(UpdateTaskRequestDTO dto, @MappingTarget TaskEntity entity);
}
