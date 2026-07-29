package com.example.controller;


import com.example.entity.StatusTask;
import com.example.entity.TaskEntity;
import com.example.repository.TaskRepository;
import com.example.service.ServiceForMainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {
    private ServiceForMainController service;
    @Autowired
    public MainController(ServiceForMainController service){
        this.service = service;
    }

    @GetMapping("/home")
    public String getMain(Model model){

        List<TaskEntity> toDoList = service.getAllTask();
        long countDone = service.getCountDone();
        long countActive = service.getCountActive();
        model.addAttribute("countDone", countDone);
        model.addAttribute("countActive",countActive);
        model.addAttribute("tasks", toDoList);
        return "main-page.html";
    }


    @PostMapping("/task/create")
    public String createTask(@RequestParam String name){
        service.addTask(name);
        return "redirect:/home";
    }

    @GetMapping("/task/remove")
    public String removeTask(@RequestParam int id){
        service.removeTask(id);
        return "redirect:/home";
    }

    @GetMapping("/task/execute")
    public String executeTask(@RequestParam int id){
        service.executeTask(id);
        return "redirect:/home";
    }

}
