package com.example.controller;


import com.example.repository.TaskRepository;
import com.example.service.ServiceForMainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    private TaskRepository taskRepository;
    private ServiceForMainController service;
    @Autowired
    public MainController(TaskRepository taskRepository,
                          ServiceForMainController service){
        this.taskRepository = taskRepository;
        this.service = service;
    }

    @GetMapping("/home")
    private String getMain(Model model){
        return "main-page.html";
    }




}
