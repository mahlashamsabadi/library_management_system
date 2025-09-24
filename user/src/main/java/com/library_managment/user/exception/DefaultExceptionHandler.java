package com.library_managment.user.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String runTimeExceptionHandler(RuntimeException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public String runTimeExceptionHandler(Exception ex){
        return ex.getMessage();
    }
}
