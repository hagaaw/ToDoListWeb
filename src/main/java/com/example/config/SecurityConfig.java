package com.example.config;

import com.example.entity.UserEntity;
import com.example.entity.UserRole;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig{
    private UserRepository userRepository;

    @Autowired
    public SecurityConfig(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth.
                        requestMatchers("/", "/main", "/registration","/login", "/error-page","/css/**").permitAll()
                        .requestMatchers("/account/**").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
                        .requestMatchers("/admin/**").hasAnyRole(UserRole.ADMIN.name())
                        .anyRequest().permitAll())
                .formLogin(form->form
                        .loginPage("/login").defaultSuccessUrl("/account").permitAll().usernameParameter("email"))
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/main").permitAll())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            UserEntity userEntity = userRepository.findByEmailIgnoreCase(username).orElseThrow(()-> new UsernameNotFoundException("Not found user with email"));
            return User.builder()
                    .username(userEntity.getEmail())
                    .password(userEntity.getPassword())
                    .roles(userEntity.getRole().name())
                    .build();
        };
    }
}