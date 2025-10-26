package com.library_managment.user.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class IdBasedAuthentication extends UsernamePasswordAuthenticationToken {


    private final Long userId;
    public IdBasedAuthentication(Object principal, Object credentials, Long userId) {
        super(principal, credentials);
        this.userId = userId;
    }

    public IdBasedAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(principal, credentials, authorities);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
