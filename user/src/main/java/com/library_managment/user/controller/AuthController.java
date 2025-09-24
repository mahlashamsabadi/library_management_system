package com.library_managment.user.controller;

import com.library_managment.user.model.dto.request.LoginRequestDto;
import com.library_managment.user.model.dto.response.LoginResponseDto;
import com.library_managment.user.service.api.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto){
        return authService.authenticate(requestDto);
    }

    @GetMapping("/logout")
    public void logout(){

    }


}
