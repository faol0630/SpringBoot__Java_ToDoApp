package com.faol.app.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data //lombok getters y setters
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime eta; //fecha estimada de la tarea
    private boolean finished;
    private TaskStatus taskStatus;
}
