package com.example.service;


import com.example.entity.mail.Mail;
import com.example.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    private JavaMailSender mailSender;
    private RegistrationRepository registration;
    private Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    public EmailService(JavaMailSender mailSender,RegistrationRepository registration) {
        this.mailSender = mailSender;
        this.registration = registration;
    }


    public void sendMail(Mail mail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getBody());

        mailSender.send(message);
    }

}
