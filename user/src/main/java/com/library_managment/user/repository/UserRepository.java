package com.library_managment.user.repository;

import com.library_managment.user.model.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser , Long> {

    Optional<ApplicationUser> getByUsername(String username);

}
