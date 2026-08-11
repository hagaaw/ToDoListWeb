package com.example.controller.common;


import com.example.entity.UserEntity;
import com.example.entity.UserRegistration;
import com.example.entity.UserRole;
import com.example.entity.mail.Mail;
import com.example.service.EmailService;
import com.example.service.RegistrationService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class EmailController {
    private EmailService emailService;
    private RegistrationService registration;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(EmailController.class);



    @Autowired
    public EmailController(UserService userService,EmailService emailService, RegistrationService registration, PasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.registration = registration;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;

    }


    @GetMapping("/mail/registration")
    public String validate(@RequestParam String email,
                              @RequestParam Integer token){
        UserRegistration userRegistration = registration.findAllByEmail(email);
        if (userRegistration == null){
            return "redirect:/registration";
        }
        else if (token.equals(registration.findAllByEmail(email).getToken())){
            UserEntity user = new UserEntity(userRegistration.getName(), userRegistration.getEmail(), passwordEncoder.encode(userRegistration.getPassword()), UserRole.USER);
            userService.save(user);
            registration.delete(userRegistration);
            return "redirect:/login";
        }
        return "redirect:/registration";
    }

    @GetMapping("/mail/registration/page")
    public String getPage(){

        return "public/mail-page";
    }

    @PostMapping("/mail/registration")
    public String takeInfo(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model){
        if (userService.haveEmail(email)){
            return "redirect:/login";
        }
        var token = RegistrationService.generateToken();
        UserRegistration user = new UserRegistration(name, email, password,token);
        registration.save(user);
        Mail mail = new Mail(email, "Валидация почты", String.valueOf(token));
        emailService.sendMail(mail);


        model.addAttribute("userRegistration", user);
        return "/public/mail-page";
    }

}
