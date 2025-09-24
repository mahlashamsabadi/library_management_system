package com.library_managment.user.service.api;

import com.library_managment.user.model.dto.request.LoginRequestDto;
import com.library_managment.user.model.dto.response.LoginResponseDto;

public interface AuthService {

    LoginResponseDto authenticate(LoginRequestDto requestDto);

}
