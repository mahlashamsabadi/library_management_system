package com.library_managment.user.model.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SignInRequestDto {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private String email;
    private String nationalCode;
    private String birthDate;
}
