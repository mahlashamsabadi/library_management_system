package com.library_managment.user.config.security.filter;

import com.library_managment.user.config.security.IdBasedAuthentication;
import com.library_managment.user.model.entity.ApplicationUser;
import com.library_managment.user.service.api.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Optional;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtService.isValid(token)) {
            throw new RuntimeException();
        }

        Optional<ApplicationUser> applicationUser = jwtService.extractUserFromToken(token);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null && applicationUser.isPresent()) {

            IdBasedAuthentication idBasedAuthentication = new IdBasedAuthentication(applicationUser.get(),
                    applicationUser.get().getRoles(), applicationUser.get().getId());
            SecurityContextHolder.getContext().setAuthentication(idBasedAuthentication);
        }

        filterChain.doFilter(request, response);
    }
}
