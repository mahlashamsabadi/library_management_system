package com.library_managment.user.service.api;

import com.library_managment.user.exception.RuleException;
import com.library_managment.user.model.dto.request.SignInRequestDto;
import com.library_managment.user.model.dto.request.LoginInfoRequestDto;
import com.library_managment.user.model.dto.request.LoginRequestDto;
import com.library_managment.user.model.dto.response.LoginResponseDto;

public interface AuthService {

    LoginResponseDto authenticate(LoginRequestDto requestDto) throws RuleException;
    LoginResponseDto getNewAccessToken(LoginInfoRequestDto loginInfoRequestDto) throws RuleException;

    void logout() throws RuleException;
    void createAccount(SignInRequestDto signInRequestDto) throws RuleException;
}
