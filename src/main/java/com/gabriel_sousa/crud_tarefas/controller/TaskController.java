package com.gabriel_sousa.crud_tarefas.controller;

import com.gabriel_sousa.crud_tarefas.dto.CreateTaskRequestDTO;
import com.gabriel_sousa.crud_tarefas.dto.GetTaskResponseDTO;
import com.gabriel_sousa.crud_tarefas.dto.UpdateTaskRequestDTO;
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

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteById(@PathVariable Long taskId,
                                     @RequestAttribute(RequestAttributeKey.USER_ID) Long userId){
        taskService.deleteTaskById(taskId, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<GetTaskResponseDTO> updateById(@RequestBody @Valid UpdateTaskRequestDTO dto,
                                                         @PathVariable Long taskId,
                                                         @RequestAttribute(RequestAttributeKey.USER_ID) Long userId){
        return ResponseEntity.ok(taskService.updateTask(dto,taskId, userId));
    }

    @PatchMapping("/conclude-task/{taskId}")
    public ResponseEntity<GetTaskResponseDTO> concludeTask(@PathVariable Long taskId,
                                                           @RequestAttribute(RequestAttributeKey.USER_ID) Long userId){
        return ResponseEntity.ok(taskService.concludeTask(taskId, userId));
    }
}