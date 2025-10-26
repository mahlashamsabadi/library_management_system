package com.library_managment.user.model.dto.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class LoginInfoRequestDto {
    private String refresh;
}
