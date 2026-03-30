package com.gabriel_sousa.crud_tarefas.service;

import com.gabriel_sousa.crud_tarefas.dto.SignUpRequestDTO;
import com.gabriel_sousa.crud_tarefas.mapper.UserMapper;
import com.gabriel_sousa.crud_tarefas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Long signUp(SignUpRequestDTO dto){
        var exists = userRepository.existsByEmail(dto.email());
        if(exists) throw new DuplicateKeyException("Email já cadastrado!");

        return userRepository.save(
                userMapper.SignUpRequestDtoToUserEntity(dto)
        ).getId();
    }
}
