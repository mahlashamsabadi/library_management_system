package com.library_managment.user.repository;

import com.library_managment.user.model.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser , Long> {

    Optional<ApplicationUser> getByUsername(String username);

}
