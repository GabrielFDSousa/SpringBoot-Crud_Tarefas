package com.gabriel_sousa.crud_tarefas.controller;

import com.gabriel_sousa.crud_tarefas.dto.SignUpRequestDTO;
import com.gabriel_sousa.crud_tarefas.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<Long> signUp(@RequestBody @Valid SignUpRequestDTO body){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(body));
    }
}
