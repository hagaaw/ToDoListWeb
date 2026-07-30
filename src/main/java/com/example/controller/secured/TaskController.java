package com.example.controller.secured;


import com.example.entity.TaskEntity;
import com.example.service.ServiceForTaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class TaskController {
    private ServiceForTaskController service;
    @Autowired
    public TaskController(ServiceForTaskController service){
        this.service = service;
    }

    @GetMapping("/account")
    public String getMain(Model model, @RequestParam(required = false) String filterMode){

        List<TaskEntity> toDoList = service.getAllTask(filterMode);
        long countDone = service.getCountDone();
        long countActive = service.getCountActive();
        model.addAttribute("countDone", countDone);
        model.addAttribute("countActive",countActive);
        model.addAttribute("tasks", toDoList);
        model.addAttribute("filterMode", filterMode);
        return "private/account-page";
    }


    @PostMapping("/account/task/create")
    public String createTask(@RequestParam String name){
        service.addTask(name);
        return "redirect:/account";
    }

    @GetMapping("/account/task/remove")
    public String removeTask(@RequestParam int id){
        service.removeTask(id);
        return "redirect:/account";
    }

    @GetMapping("/account/task/execute")
    public String executeTask(@RequestParam int id){
        service.executeTask(id);
        return "redirect:/account";
    }
}
