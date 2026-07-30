package com.example.controller.common;


import com.example.service.ServiceForMainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private ServiceForMainController service;

    @Autowired
    public MainController(ServiceForMainController service){
        this.service = service;
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "public/sign-up-page";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "public/login-page";
    }

    @GetMapping("/main")
    public String getMainMenu(){
        return "public/landing-page";
    }

    @GetMapping("/")
    public String redirectToMain(){
        return "redirect:/main";
    }
}
