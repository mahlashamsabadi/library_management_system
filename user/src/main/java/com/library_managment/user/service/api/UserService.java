package com.library_managment.user.service.api;

import com.library_managment.user.model.entity.ApplicationUser;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserService {

    Optional<ApplicationUser> getUserById(Long id);

    Optional<ApplicationUser> getUserByUsername(String username);

    ApplicationUser save(ApplicationUser applicationUser);


}
