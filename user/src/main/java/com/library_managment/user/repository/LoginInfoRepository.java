package com.library_managment.user.repository;

import com.library_managment.user.model.entity.ApplicationUser;
import com.library_managment.user.model.entity.LoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo, Long> {
    Optional<LoginInfo> getLoginInfoByRefresh(String refresh);
    Optional<LoginInfo> getLoginInfoByUserAndExpirationDateAfter(ApplicationUser user, LocalDateTime date);
}
