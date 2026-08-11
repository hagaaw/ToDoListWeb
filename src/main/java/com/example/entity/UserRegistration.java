package com.example.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="registrations")
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private LocalDateTime createdAt;

    private Integer token;

    public UserRegistration() {
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreateAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public UserRegistration(String name,String email, String password, Integer token) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.token = token;
    }

    @PrePersist
    private void onCreate(){
        createdAt = LocalDateTime.now();
    }
}
