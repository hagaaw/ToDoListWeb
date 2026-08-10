package com.example.service;


import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void save(UserEntity user){
        userRepository.save(user);
    }

    public UserEntity getCurrentUser(){
        return userRepository.findByEmailIgnoreCase(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(()-> new IllegalArgumentException("User not foundc"));
    }
    public boolean haveEmail(String email) {
        return userRepository.findAllByEmail(email).isPresent();
    }

}
