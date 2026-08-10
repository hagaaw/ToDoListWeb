package com.example.repository;

import com.example.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<UserRegistration,Integer> {
    Optional<UserRegistration> findAllByEmail(String email);
}
