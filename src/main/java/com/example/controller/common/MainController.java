package com.example.controller.common;


import com.example.entity.UserEntity;
import com.example.service.UserService;
import com.example.entity.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public MainController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

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


    @PostMapping("/registration")
    public String createUserAccount(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String password){
        UserEntity user = new UserEntity(name, email, passwordEncoder.encode(password), UserRole.USER);
        userService.save(user);
        return "redirect:/login";
    }
}
