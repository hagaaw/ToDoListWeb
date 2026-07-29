package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@ControllerAdvice
public class GlobalExceptionHandler {
    @GetMapping("/error-page")
    public String getErrorPage(){
        return "error-page";
    }

    @ExceptionHandler(Throwable.class)
    public String handleThrowable(Throwable throwable){
        return "redirect:/error-page";
    }

}
