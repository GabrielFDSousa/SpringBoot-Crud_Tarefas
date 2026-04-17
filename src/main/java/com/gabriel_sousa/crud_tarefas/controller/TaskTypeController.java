package com.gabriel_sousa.crud_tarefas.controller;

import com.gabriel_sousa.crud_tarefas.dto.CreateTaskTypeRequestDTO;
import com.gabriel_sousa.crud_tarefas.dto.GetTaskTypeResponseDTO;
import com.gabriel_sousa.crud_tarefas.dto.UpdateTaskTypeRequestDTO;
import com.gabriel_sousa.crud_tarefas.security.RequestAttributeKey;
import com.gabriel_sousa.crud_tarefas.service.TaskTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/taskTypes")
public class TaskTypeController {

    private final TaskTypeService taskTypeService;

    @PostMapping
    public ResponseEntity createTaskType(@RequestBody @Valid CreateTaskTypeRequestDTO dto,
                                         @RequestAttribute(RequestAttributeKey.USER_ID) Long userId){
        taskTypeService.createTaskType(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetTaskTypeResponseDTO>> getAllByUser(@RequestAttribute(RequestAttributeKey.USER_ID) Long userId){
        return ResponseEntity.ok().body(
                taskTypeService.getAllByUser(userId)
        );
    }

    @PutMapping("/{taskTypeId}")
    public ResponseEntity<GetTaskTypeResponseDTO> updateTaskTypeById(
            @RequestBody @Valid UpdateTaskTypeRequestDTO dto,
            @PathVariable Long taskTypeId,
            @RequestAttribute(RequestAttributeKey.USER_ID) Long userId
    ){
        return ResponseEntity.ok(taskTypeService.updateTaskType(dto, taskTypeId, userId));
    }

    @DeleteMapping("/{taskTypeId}")
    public ResponseEntity deleteTaskTypeById(
            @PathVariable Long taskTypeId,
            @RequestAttribute(RequestAttributeKey.USER_ID) Long userId
            ){
        taskTypeService.deleteTaskTypeById(taskTypeId, userId);
        return ResponseEntity.noContent().build();
    }
}
