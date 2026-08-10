package com.example.controller.common;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {


    @GetMapping("/registration")
    public String getRegistration(){
        return "public/sign-up-page";
    }

    @GetMapping("/login")
    public String getLogin(Model model, @RequestParam(required = false) String error){
        if (error != null){
            model.addAttribute("isError", true);
        }
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
