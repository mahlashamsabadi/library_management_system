package com.library_managment.user.exception;

import com.library_managment.user.model.dto.response.DefaultResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(RuleException.class)
    public DefaultResponseDto<Object> ruleExceptionHandler(RuleException ex){
        return DefaultResponseDto.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).data(null).build();
    }

    @ExceptionHandler(DataException.class)
    public DefaultResponseDto<Object> dataExceptionHandler(DataException ex){
        return DefaultResponseDto.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).data(null).build();
    }


    @ExceptionHandler(RuntimeException.class)
    public DefaultResponseDto<Object> runTimeExceptionHandler(RuntimeException ex){
        return DefaultResponseDto.builder().message(ex.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).data(null).build();

    }

    @ExceptionHandler(Exception.class)
    public DefaultResponseDto<Object> exceptionHandler(RuntimeException ex){
        return DefaultResponseDto.builder().message(ex.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).data(null).build();
    }
}
