package com.library_managment.user.service.imp;

import com.library_managment.user.model.dto.request.LoginRequestDto;
import com.library_managment.user.model.dto.response.LoginResponseDto;
import com.library_managment.user.model.entity.ApplicationUser;
import com.library_managment.user.service.api.AuthService;
import com.library_managment.user.service.api.JWTService;
import com.library_managment.user.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JWTService jwtService;

    public LoginResponseDto authenticate(LoginRequestDto requestDto){

        ApplicationUser user = userService.getUserByUsername(requestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("user not found"));

        if(!user.getPassword().equals(requestDto.getPassword())) {
            throw new RuntimeException("password is incorrect");
        }

        String access = jwtService.generateToken(user);

        return LoginResponseDto.builder()
                .access(access)
                .refresh(null)
                .build();
    }

}
