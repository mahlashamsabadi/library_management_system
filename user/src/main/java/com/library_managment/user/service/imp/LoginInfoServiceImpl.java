package com.library_managment.user.service.imp;

import com.library_managment.user.exception.DataException;
import com.library_managment.user.model.entity.ApplicationUser;
import com.library_managment.user.model.entity.LoginInfo;
import com.library_managment.user.repository.LoginInfoRepository;
import com.library_managment.user.service.api.LoginInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginInfoServiceImpl implements LoginInfoService {

    private final LoginInfoRepository loginInfoRepository;

    @Override
    public LoginInfo save(LoginInfo loginInfo) {
        return loginInfoRepository.save(loginInfo);
    }

    @Override
    public LoginInfo getLoginInfoByRefresh(String refresh) {
        return loginInfoRepository.getLoginInfoByRefresh(refresh)
                .orElseThrow(() -> new DataException("refresh token is not valid"));
    }

    @Override
    public LoginInfo getLoginInfoByUser(ApplicationUser user) {
        return loginInfoRepository.getLoginInfoByUserAndExpirationDateAfter(user, LocalDateTime.now())
                .orElseThrow(() -> new DataException("no loginInfo found for this user"));
    }
}
