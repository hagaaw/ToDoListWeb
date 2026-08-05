package com.example.service;


import com.example.entity.StatusTask;
import com.example.entity.TaskEntity;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    private final UserService userService;
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService){
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public long getCountDone() {
        return taskRepository.findByStatusTaskAndUserId(StatusTask.DONE, userService.getCurrentUser().getId()).size();
    }

    public int getCountActive() {
        return taskRepository.findByStatusTaskAndUserId(StatusTask.ACTIVE,userService.getCurrentUser().getId()).size();
    }

    public List<TaskEntity> getAllTask(String filterMode) {
        if(filterMode == null || filterMode.equals("ALL")){
            return taskRepository.findAllByUserId(userService.getCurrentUser().getId());
        }

        return taskRepository.findByStatusTaskAndUserId(StatusTask.valueOf(filterMode),userService.getCurrentUser().getId());
    }

    public void addTask(String name) {
        taskRepository.save(new TaskEntity(name, StatusTask.ACTIVE, userService.getCurrentUser()));
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

}
