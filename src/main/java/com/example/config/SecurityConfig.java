//package com.example.config;
//
//
//import com.example.entity.UserRole;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http){
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth->auth
//                        .requestMatchers("/", "/main", "/error-page","/registration", "/login").permitAll()
//                        .requestMatchers("/account/**").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
//                        .requestMatchers("/admin/**").hasAnyRole(UserRole.ADMIN.name())).build();
//    }
//}
