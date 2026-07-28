package com.example.service;


import com.example.entity.StatusTask;
import com.example.entity.TaskEntity;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceForMainController {
    private TaskRepository taskRepository;

    @Autowired
    public ServiceForMainController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public long getCountDone() {
        return taskRepository.findByStatusTask(StatusTask.DONE).size();
    }

    public int getCountActive() {
        return taskRepository.findByStatusTask(StatusTask.ACTIVE).size();
    }

    public List<TaskEntity> getAllTask() {
        return taskRepository.findAll();
    }
}
