package com.library_managment.user.service.imp;

import com.library_managment.user.exception.DataException;
import com.library_managment.user.exception.RuleException;
import com.library_managment.user.model.dto.request.LoginInfoRequestDto;
import com.library_managment.user.model.dto.request.LoginRequestDto;
import com.library_managment.user.model.dto.request.SignInRequestDto;
import com.library_managment.user.model.dto.response.LoginResponseDto;
import com.library_managment.user.model.entity.ApplicationUser;
import com.library_managment.user.model.entity.LoginInfo;
import com.library_managment.user.model.entity.Person;
import com.library_managment.user.service.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PersonService personService;
    private final JWTService jwtService;
    private final LoginInfoService loginInfoService;

    @Value("${jwt.refresh.token.expiration.duration}")
    private Long expirationDuration;

    public LoginResponseDto authenticate(LoginRequestDto requestDto) throws RuleException {

        ApplicationUser user = userService.getUserByUsername(requestDto.getUsername())
                .orElseThrow(() -> new DataException("user not found"));

        if(!user.getPassword().equals(requestDto.getPassword())) {
            throw new RuleException("password is incorrect");
        }

        String access = jwtService.generateToken(user);
        String refresh = jwtService.generateRefreshToken(user);
        LoginInfo loginInfo = LoginInfo.builder().refresh(refresh).user(user).creationDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now().plusSeconds(expirationDuration)).build();
        loginInfoService.save(loginInfo);

        return LoginResponseDto.builder()
                .access(access)
                .refresh(refresh)
                .build();
    }

    @Override
    public LoginResponseDto getNewAccessToken(LoginInfoRequestDto loginInfoRequestDto) throws RuleException {
        LoginInfo loginInfo = loginInfoService.getLoginInfoByRefresh(loginInfoRequestDto.getRefresh());
        if (jwtService.isExpired(loginInfo)) {
            throw new RuleException("refresh token is expired");
        }
        String access = jwtService.generateToken(loginInfo.getUser());
        return LoginResponseDto.builder().access(access).refresh(loginInfoRequestDto.getRefresh()).build();
    }

    @Override
    public void logout() throws RuleException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuleException("user is not authenticated");
        }

        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
        LoginInfo loginInfo = loginInfoService.getLoginInfoByUser(user);
        loginInfo.setExpirationDate(LocalDateTime.now());
    }

    @Override
    public void createAccount(SignInRequestDto signInRequestDto) throws RuleException {
        Person person = Person.builder().firstName(signInRequestDto.getFirstName())
                .lastName(signInRequestDto.getLastName())
                .birthDate(LocalDateTime.parse(signInRequestDto.getBirthDate()))
                .nationalCode(signInRequestDto.getNationalCode())
                .phone(signInRequestDto.getPhoneNumber())
                .build();
        person  = personService.savePerson(person);
        ApplicationUser user = ApplicationUser.builder().username(signInRequestDto.getUsername()).email(signInRequestDto.getEmail())
                .password(signInRequestDto.getPassword()).creationDate(new Date()).effectiveDate(LocalDateTime.now())
                .lastModificationDate(new Date()).person(person).build();
        userService.save(user);

    }
}
