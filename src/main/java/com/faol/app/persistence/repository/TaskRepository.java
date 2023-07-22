package com.faol.app.persistence.repository;

import com.faol.app.persistence.entity.TaskEntity;
import com.faol.app.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

     List<TaskEntity> findAllByTaskStatus(TaskStatus taskStatus);

    @Modifying
    @Query(
            value = "UPDATE TASK SET FINISHED=true WHERE ID= :id",
            nativeQuery = true
    )
     void markTaskAsFinished(@Param("id") Long id);

    @Modifying
    @Query(
            value = "UPDATE TASK SET TASK_STATUS=1 WHERE ID= :id",
            nativeQuery = true

    )
     void markTaskStatusAsLate(@Param("id") Long id);

    @Modifying
    @Query(
            value = "DELETE FROM TASK WHERE TITLE = :title",
            nativeQuery = true
    )
    public void deleteTaskByTitle(@Param("title") String title);

    @Modifying
    @Query(
            value = "SELECT * FROM TASK WHERE TITLE = :title",
            nativeQuery = true
    )
     List<TaskEntity> findTaskByTitle(@Param("title") String title);

}
