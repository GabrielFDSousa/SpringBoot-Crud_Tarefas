package com.gabriel_sousa.crud_tarefas.repository;

import com.gabriel_sousa.crud_tarefas.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByEmail(String email);
}
