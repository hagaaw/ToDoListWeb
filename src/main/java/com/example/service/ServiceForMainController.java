package com.example.service;


import com.example.entity.StatusTask;
import com.example.entity.TaskEntity;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;

import java.util.List;
import java.util.Optional;

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

    public List<TaskEntity> getAllTask(String filterMode) {
        if(filterMode == null || filterMode.equals("ALL")){
            return taskRepository.findAll();
        }

        return taskRepository.findByStatusTask(StatusTask.valueOf(filterMode));
    }

    public void addTask(String name) {
        taskRepository.save(new TaskEntity(name, StatusTask.ACTIVE));
    }

    public void removeTask(int id) {
        taskRepository.deleteById(id);
    }

    public void executeTask(int id) {
        TaskEntity task = taskRepository.findById(id).orElse(null);
        if (task != null){
            task.setStatusTask(StatusTask.DONE);
            taskRepository.save(task);
        }
    }

    public List<TaskEntity> sortTask(StatusTask forSort) {
        return taskRepository.findByStatusTask(forSort);
    }
}
