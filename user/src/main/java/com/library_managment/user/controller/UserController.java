package com.library_managment.user.controller;


import com.library_managment.user.exception.RuleException;
import com.library_managment.user.model.dto.request.LoginRequestDto;
import com.library_managment.user.model.dto.response.DefaultResponseDto;
import com.library_managment.user.model.dto.response.LoginResponseDto;
import com.library_managment.user.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

}
