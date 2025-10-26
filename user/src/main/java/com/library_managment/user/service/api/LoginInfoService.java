package com.library_managment.user.service.api;

import com.library_managment.user.model.entity.ApplicationUser;
import com.library_managment.user.model.entity.LoginInfo;


public interface LoginInfoService {
    LoginInfo save(LoginInfo loginInfo);
    LoginInfo getLoginInfoByRefresh(String refresh);
    LoginInfo getLoginInfoByUser(ApplicationUser user);
}
