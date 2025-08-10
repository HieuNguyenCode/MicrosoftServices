package com.example.auth_service.controller;

import com.example.auth_service.dto.LoginRequest;
import com.example.auth_service.dto.RefreshRequest;
import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class    AuthenticationController {
    private  final UserService userService;

    @PostMapping("/register")
    public CompletableFuture<ResponseEntity<?>> Register(@RequestBody @Valid RegisterRequest request) {
        return userService.Register(request);
    }

    @PostMapping("/login")
    public CompletableFuture<ResponseEntity<?>> Login(@RequestBody @Valid LoginRequest request) {
        return userService.Login(request);
    }

    @PatchMapping("/refresh")
    public CompletableFuture<ResponseEntity<?>> Refresh(@RequestBody @Valid RefreshRequest request, @RequestHeader("Authorization") String authorizationHeader) {
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }
        return userService.Refresh(request, token);
    }
}
