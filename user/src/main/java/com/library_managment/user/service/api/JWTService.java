package com.library_managment.user.service.api;

import com.library_managment.user.model.entity.ApplicationUser;
import com.library_managment.user.model.entity.LoginInfo;

import java.security.Key;
import java.util.Optional;

public interface JWTService {

    String generateToken(ApplicationUser user);
    String generateRefreshToken(ApplicationUser user);

    boolean isValid(String token);

    Key generateSigningKey();

    boolean isExpired(LoginInfo loginInfo);

    Optional<ApplicationUser> extractUserFromToken(String token);
}
