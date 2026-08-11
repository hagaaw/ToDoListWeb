package com.example.service;


import com.example.entity.UserEntity;
import com.example.entity.UserRegistration;
import com.example.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class RegistrationService {

    private RegistrationRepository registration;

    @Autowired
    public RegistrationService(JavaMailSender mailSender, RegistrationRepository registration) {
        this.registration = registration;
    }

    public static int generateToken(){
        Random rand = new Random();
        return rand.nextInt(100000, 999999);
    }


    public UserRegistration findAllByEmail(String email){
        return registration.findAllByEmail(email).orElse(null);
    }

    public void save(UserRegistration user) {
        registration.save(user);
    }

    public void delete(UserRegistration user) {
        registration.delete(user);
    }

    public void deletByCreatedAtBefore(LocalDateTime cutoff) {
        registration.deleteByCreatedAtBefore(cutoff);
    }
}
