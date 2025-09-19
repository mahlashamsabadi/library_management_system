package com.library_managment.user.service.imp;

import com.library_managment.user.model.entity.ApplicationUser;
import com.library_managment.user.service.api.JWTService;
import com.library_managment.user.service.api.UserService;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class JWTServiceImpl implements JWTService {

    private JwtParser jwtParser;
    private UserService userService;

    private final String SECRET_KEY = "QW9ER643TTgdYYlsefMlno";
    private final String ROLE_CLAIM = "roleClaim";
    private final String ID_CLAIM = "idClaim";
    private final long  ACCESS_TOKEN_EXPIRATION_DURATION = 1000 * 60 * 60;

    @Override
    public String generateToken(ApplicationUser user) {
        Map<String , Object> extraClaims = new HashMap<>();
        extraClaims.put(ROLE_CLAIM, user.getRoles());
        extraClaims.put(ID_CLAIM, user.getId());
        return Jwts.builder()
                .setClaims(extraClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_DURATION))
                .signWith(generateSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean tokenIsValid(String token) {
        Long userId = (Long) jwtParser.parseClaimsJws(token).getBody().get(ID_CLAIM);
        boolean notExpired = jwtParser.parseClaimsJws(token).getBody().getExpiration()
                .after(new Date(System.currentTimeMillis()));
        Optional<ApplicationUser> user  = userService.getUserById(userId);
        return user.isPresent() && notExpired ? true : false;
    }

    public Key generateSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
