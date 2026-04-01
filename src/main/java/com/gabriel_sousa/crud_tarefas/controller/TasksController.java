package com.gabriel_sousa.crud_tarefas.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    @GetMapping
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok().body("hello world!");
    }
}
