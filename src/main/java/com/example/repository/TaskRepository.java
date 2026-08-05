package com.example.repository;

import com.example.entity.StatusTask;
import com.example.entity.TaskEntity;
import com.example.entity.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    List<TaskEntity> findByStatusTaskAndUserId(StatusTask statusTask, int user_id);

    List<TaskEntity> findAllByUserId(int user_id);
}
