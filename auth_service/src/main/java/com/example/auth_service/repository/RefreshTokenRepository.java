package com.example.auth_service.repository;

import com.example.auth_service.entity.Refreshtoken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<Refreshtoken, String> {
}
