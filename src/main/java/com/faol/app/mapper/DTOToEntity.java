package com.faol.app.mapper;

import com.faol.app.persistence.entity.TaskEntity;
import com.faol.app.persistence.entity.TaskStatus;
import com.faol.app.service.dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DTOToEntity implements IMapper<TaskDTO, TaskEntity>{
    @Override
    public TaskEntity map(TaskDTO taskDTO) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskDTO.getTitle());
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setEta(taskDTO.getEta());
        taskEntity.setCreatedDate(LocalDateTime.now());
        taskEntity.setTaskStatus(TaskStatus.ON_TIME);
        taskEntity.setFinished(false);
        return taskEntity;
    }
}
//El usuario ingresa los datos en el TaskDTO.
//Con este mapper se genera el TaskEntity
//que es el parametro esperado por el metodo createTask.
