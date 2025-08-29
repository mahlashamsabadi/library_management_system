package com.library_managment.user.service;

import com.library_managment.user.model.entity.BookStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void func(){
        BookStatus status = BookStatus.builder()
                .code("Code1")
                .name("1")
                .description("")
                .build();
    }

}
