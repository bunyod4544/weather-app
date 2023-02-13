package com.example.weatherapp.repository;

import com.example.weatherapp.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    AuthUser findByUsername(String username);

    boolean existsByUsername(String username);
}
