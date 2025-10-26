package com.library_managment.user.controller;

import com.library_managment.user.exception.RuleException;
import com.library_managment.user.model.dto.request.SignInRequestDto;
import com.library_managment.user.model.dto.request.LoginInfoRequestDto;
import com.library_managment.user.model.dto.request.LoginRequestDto;
import com.library_managment.user.model.dto.response.DefaultResponseDto;
import com.library_managment.user.model.dto.response.LoginResponseDto;
import com.library_managment.user.service.api.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public DefaultResponseDto<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) throws RuleException {
        LoginResponseDto loginResponseDto = authService.authenticate(requestDto);
        return DefaultResponseDto.<LoginResponseDto>builder().data(loginResponseDto).message(null)
                .status(HttpStatus.OK).build();
    }

    @PostMapping ("/refresh-token")
    public DefaultResponseDto<LoginResponseDto> refreshToken(@RequestBody LoginInfoRequestDto loginInfoRequestDto) throws RuleException {
        LoginResponseDto newAccessToken = authService.getNewAccessToken(loginInfoRequestDto);
        return DefaultResponseDto.<LoginResponseDto>builder().data(newAccessToken).message(null)
                .status(HttpStatus.OK).build();
    }

    @GetMapping("/logout")
    public DefaultResponseDto<Object> logout() throws RuleException {
        authService.logout();
        return DefaultResponseDto.builder().data(null).status(HttpStatus.OK).message(null).build();
    }

    @PostMapping("/signIn")
    public DefaultResponseDto<Object> signIn(@RequestBody SignInRequestDto requestDto) throws RuleException {
        authService.createAccount(requestDto);
        return DefaultResponseDto.builder().data(null).status(HttpStatus.OK).message(null).build();

    }

}
