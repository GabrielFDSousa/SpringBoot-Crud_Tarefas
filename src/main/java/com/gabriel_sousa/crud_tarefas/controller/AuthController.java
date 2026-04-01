package com.gabriel_sousa.crud_tarefas.controller;

import com.gabriel_sousa.crud_tarefas.dto.SignInRequestDTO;
import com.gabriel_sousa.crud_tarefas.dto.SignInResponseDTO;
import com.gabriel_sousa.crud_tarefas.dto.SignUpRequestDTO;
import com.gabriel_sousa.crud_tarefas.entity.UserEntity;
import com.gabriel_sousa.crud_tarefas.security.TokenService;
import com.gabriel_sousa.crud_tarefas.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody @Valid SignUpRequestDTO body){
        authService.signUp(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody @Valid SignInRequestDTO body){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.email(), body.password())
        );
        String token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok(new SignInResponseDTO(token));
    }
}
