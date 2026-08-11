package com.example.repository;

import com.example.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<UserRegistration,Integer> {
    Optional<UserRegistration> findAllByEmail(String email);


    @Modifying
    @Query("DELETE FROM UserRegistration r where r.createdAt < :cutoff")
    void deleteByCreatedAtBefore(LocalDateTime cutoff);

}
