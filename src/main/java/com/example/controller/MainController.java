package com.example.controller;


import com.example.entity.StatusTask;
import com.example.entity.TaskEntity;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private TaskRepository taskRepository;


    @GetMapping("/home")
    private String getMain(Model model){
        return "main-page.html";
    }




}
