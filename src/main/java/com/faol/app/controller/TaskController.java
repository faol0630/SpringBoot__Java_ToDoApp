package com.faol.app.controller;

import com.faol.app.persistence.entity.TaskEntity;
import com.faol.app.persistence.entity.TaskStatus;
import com.faol.app.service.TaskService;
import com.faol.app.service.dto.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    //inyeccion de dependencias a traves del constructor:
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping
    public TaskEntity createTask(@RequestBody TaskDTO taskDTO){
        return this.taskService.createTask(taskDTO);
    }

    @GetMapping
    public List<TaskEntity> findAll(){
        return taskService.findAll();
    }

    @GetMapping("/get/{id}")
    public Optional<TaskEntity> findById(@PathVariable Long id){
        return taskService.findById(id);
    }

    @GetMapping("/status/{task_status}")
    public List<TaskEntity> findAllByTaskStatus(@PathVariable TaskStatus task_status){
        return taskService.findAllByTaskStatus(task_status);
    }

    @GetMapping("/{title}")
    public List<TaskEntity> findTaskByTitle(@PathVariable String title){
        return taskService.findTaskByTitle(title);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> markTaskAsFinished(@PathVariable Long id){
        taskService.markTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }
    //este metodo normalmente no devuelve nada.
    //en este caso retorna codigo 204.
    //sin el ResponseEntity<Void> en caso de exito, devuelve un codigo 200

    @PatchMapping("/update_task_status/{id}")
    public ResponseEntity<Void> markTaskStatusAsLate(@PathVariable Long id){
        taskService.markTaskStatusAsLate(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        taskService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<Void> deleteTaskByTitle(@PathVariable String title){
        taskService.deleteTaskByTitle(title);
        return ResponseEntity.noContent().build();
    }
}
