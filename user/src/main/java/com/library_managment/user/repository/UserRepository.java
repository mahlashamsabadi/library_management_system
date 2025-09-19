package com.library_managment.user.repository;

import com.library_managment.user.model.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser , Long> {
}
