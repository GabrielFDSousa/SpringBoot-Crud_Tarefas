package com.gabriel_sousa.crud_tarefas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 160)
    private String title;

    @Column(length = 400)
    private String description;

    private boolean completed = false;

    private LocalDateTime created_at = LocalDateTime.now();

    private LocalDateTime completed_at;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "taskTypeId", nullable = false)
    private TaskTypeEntity taskType;
}
