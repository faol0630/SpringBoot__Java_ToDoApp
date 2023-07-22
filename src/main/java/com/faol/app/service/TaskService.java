package com.faol.app.service;

import com.faol.app.exceptions.ToDoExceptions;
import com.faol.app.mapper.DTOToEntity;
import com.faol.app.persistence.entity.TaskEntity;
import com.faol.app.persistence.entity.TaskStatus;
import com.faol.app.persistence.repository.TaskRepository;
import com.faol.app.service.dto.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    //inyeccion de dependencias a traves del constructor
    private final TaskRepository taskRepository;
    private final DTOToEntity mapper;

    public TaskService(TaskRepository taskRepository, DTOToEntity mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public TaskEntity createTask(TaskDTO taskDTO){
        //antes de ingresar logica aca, primero crear TaskDTO y
        //despues crear mapper, para usarlo entre Task y TaskDTO.

        //recibe el DTO que usó el usuario para ingresar datos
        //y lo convierte a la entity Task
        TaskEntity taskEntity = mapper.map(taskDTO);

        //el metodo de repository solo recibe Task.por eso el proceso anterior
        return this.taskRepository.save(taskEntity);

    }

    public List<TaskEntity> findAll(){
        return this.taskRepository.findAll();
    }

    public Optional<TaskEntity> findById(Long id){
        Optional<TaskEntity> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        return taskRepository.findById(id);
    }

    public List<TaskEntity> findAllByTaskStatus(TaskStatus taskStatus){
        return  this.taskRepository.findAllByTaskStatus(taskStatus);
    }

    @Transactional
    public List<TaskEntity> findTaskByTitle(String title){
        return taskRepository.findTaskByTitle(title);
    }

    @Transactional //se ejecuta toda la funcion o nada. para evitar errores de metodos ejecutadps a medias.
    public void markTaskAsFinished( Long id){
        //antes de la siguiente logica debe estar creada la class ToDoExceptions
        //primero buscamos el elemento(Task) usando el Id
        Optional<TaskEntity> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        } else if (optionalTask.get().getTaskStatus() == TaskStatus.LATE) {
            throw new ToDoExceptions("task delayed.cannot be finished yet", HttpStatus.CONFLICT);//409
        }
        this.taskRepository.markTaskAsFinished(id);
    }

    @Transactional
    public void markTaskStatusAsLate(Long id){
        Optional<TaskEntity> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        } else if (optionalTask.get().isFinished()) {
            throw new ToDoExceptions("task already finished", HttpStatus.CONFLICT); //409
        }
        this.taskRepository.markTaskStatusAsLate(id);
    }

    public void deleteById(Long id){
        //buscamos el task por medio del Id
        Optional<TaskEntity> optionalTask = this.taskRepository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        taskRepository.deleteById(id);
    }

    public void deleteAll(){
        List<TaskEntity> list = this.taskRepository.findAll();
        if (list.isEmpty()){
            throw new ToDoExceptions("Nothing to delete", HttpStatus.NO_CONTENT);
        }
        taskRepository.deleteAll();
    }
    @Transactional
    public void deleteTaskByTitle(String title){

        taskRepository.deleteTaskByTitle(title);
    }


}


