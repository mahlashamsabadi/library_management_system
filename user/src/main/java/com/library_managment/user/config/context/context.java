package com.library_managment.user.config.context;

import io.jsonwebtoken.JwtParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class context {

    @Bean
    public JwtParser getJwtParser(){
        return null;
    }

}
