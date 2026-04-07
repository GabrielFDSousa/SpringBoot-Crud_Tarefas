package com.gabriel_sousa.crud_tarefas.mapper;

import com.gabriel_sousa.crud_tarefas.dto.CreateTaskTypeRequestDTO;
import com.gabriel_sousa.crud_tarefas.dto.GetTaskTypeResponseDTO;
import com.gabriel_sousa.crud_tarefas.entity.TaskTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskTypeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    TaskTypeEntity CreateTaskTypeRequestDtoToTaskTypeEntity(CreateTaskTypeRequestDTO dto, Long userId);

    @Mapping(source = "user.id", target = "userId")
    GetTaskTypeResponseDTO entityToGetResponseDTO(TaskTypeEntity entity);

    List<GetTaskTypeResponseDTO> entitiesToListGetResponseDTO(List<TaskTypeEntity> entities);
}
