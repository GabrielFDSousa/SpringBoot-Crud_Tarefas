package com.gabriel_sousa.crud_tarefas.mapper;

import com.gabriel_sousa.crud_tarefas.dto.SignUpRequestDTO;
import com.gabriel_sousa.crud_tarefas.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity SignUpRequestDtoToUserEntity(SignUpRequestDTO dto);
}
