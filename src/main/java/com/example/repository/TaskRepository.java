package com.example.repository;

import com.example.entity.StatusTask;
import com.example.entity.TaskEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    List<TaskEntity> findByStatusTask(StatusTask statusTask);
}
