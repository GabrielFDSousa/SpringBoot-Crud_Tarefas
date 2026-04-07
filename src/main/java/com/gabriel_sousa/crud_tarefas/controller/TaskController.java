package com.gabriel_sousa.crud_tarefas.controller;

import com.gabriel_sousa.crud_tarefas.dto.CreateTaskRequestDTO;
import com.gabriel_sousa.crud_tarefas.dto.GetTaskResponseDTO;
import com.gabriel_sousa.crud_tarefas.security.RequestAttributeKey;
import com.gabriel_sousa.crud_tarefas.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity createTask(@RequestBody @Valid CreateTaskRequestDTO dto,
                                     @RequestAttribute(RequestAttributeKey.USER_ID) Long userId){
        taskService.createTask(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetTaskResponseDTO>> getAllByUser(@RequestAttribute(RequestAttributeKey.USER_ID) Long userId){
        return ResponseEntity.ok().body(
                taskService.getAllByUser(userId)
        );
    }
}