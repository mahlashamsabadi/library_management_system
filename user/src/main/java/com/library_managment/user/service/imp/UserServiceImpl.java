package com.library_managment.user.service.imp;

import com.library_managment.user.model.entity.ApplicationUser;
import com.library_managment.user.repository.UserRepository;
import com.library_managment.user.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public Optional<ApplicationUser> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<ApplicationUser> getUserByUsername(String username){
        return userRepository.getByUsername(username);
    }

}
