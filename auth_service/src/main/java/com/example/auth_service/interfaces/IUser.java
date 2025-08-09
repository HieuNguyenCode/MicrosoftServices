package com.example.auth_service.interfaces;

import com.example.auth_service.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface IUser {
    CompletableFuture<ResponseEntity<?>> RegisterRequest(RegisterRequest request);
//    CompletableFuture<ResponseEntity<?>> Login(String userName, String password);
}
