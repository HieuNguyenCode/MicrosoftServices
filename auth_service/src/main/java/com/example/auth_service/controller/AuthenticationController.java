package com.example.auth_service.controller;

import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class    AuthenticationController {
    private  final UserService userService;

    @PostMapping("/register")
    public CompletableFuture<ResponseEntity<?>> RegisterRequest(@RequestBody @Valid RegisterRequest request) {
        System.out.println(">>> Register API called: " + request);
        return userService.RegisterRequest(request);
    }
}
