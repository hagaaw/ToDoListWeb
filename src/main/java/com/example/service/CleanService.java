package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CleanService {
    @Autowired
    private RegistrationService registrationService;

    @Scheduled(fixedRate = 30000)
    public void clean(){
        LocalDateTime cutoff = LocalDateTime.now().minusSeconds(30);
        registrationService.deletByCreatedAtBefore(cutoff);
    }
}
