package com.gabriel_sousa.crud_tarefas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_types_taks")
public class TaskTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 160)
    private String name;

    private LocalDateTime created_at = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "taskType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TaskEntity> tasks = new HashSet<>();
}
