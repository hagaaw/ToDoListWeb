package com.example.controller.secured;


import com.example.entity.TaskEntity;
import com.example.service.TaskService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class TaskController {
    private TaskService taskService;
    private UserService userService;


    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }


    @GetMapping("/account")
    public String getMain(Model model, @RequestParam(required = false) String filterMode){

        List<TaskEntity> toDoList = taskService.getAllTask(filterMode);
        long countDone = taskService.getCountDone();
        long countActive = taskService.getCountActive();
        model.addAttribute("userName", userService.getCurrentUser().getName());
        model.addAttribute("countDone", countDone);
        model.addAttribute("countActive",countActive);
        model.addAttribute("tasks", toDoList);
        model.addAttribute("filterMode", filterMode);
        return "private/account-page";
    }


    @PostMapping("/account/task/create")
    public String createTask(@RequestParam String name){
        taskService.addTask(name);
        return "redirect:/account";
    }

    @GetMapping("/account/task/remove")
    public String removeTask(@RequestParam int id){
        taskService.removeTask(id);
        return "redirect:/account";
    }

    @GetMapping("/account/task/execute")
    public String executeTask(@RequestParam int id){
        taskService.executeTask(id);
        return "redirect:/account";
    }
}
