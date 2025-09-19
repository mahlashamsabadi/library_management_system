package com.library_managment.user.service.api;

import com.library_managment.user.model.entity.ApplicationUser;

import java.security.Key;

public interface JWTService {

    String generateToken(ApplicationUser user);

    boolean tokenIsValid(String token);

    Key generateSigningKey();

}
