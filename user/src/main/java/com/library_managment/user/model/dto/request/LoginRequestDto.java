package com.library_managment.user.model.dto.request;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String username;
    private String password;

}
