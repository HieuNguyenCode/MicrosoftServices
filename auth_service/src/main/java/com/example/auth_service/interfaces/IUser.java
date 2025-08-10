package com.example.auth_service.interfaces;

import com.example.auth_service.dto.LoginRequest;
import com.example.auth_service.dto.RefreshRequest;
import com.example.auth_service.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface IUser {
    CompletableFuture<ResponseEntity<?>> Register(RegisterRequest request);
    CompletableFuture<ResponseEntity<?>> Login(LoginRequest request);
    CompletableFuture<ResponseEntity<?>> Refresh(RefreshRequest request, String token);
}
