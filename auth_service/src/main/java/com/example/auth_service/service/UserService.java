package com.example.auth_service.service;

import com.example.auth_service.dto.LoginRequest;
import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.entity.UserEntity;
import com.example.auth_service.interfaces.IUser;
import com.example.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserService implements IUser {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> RegisterRequest(RegisterRequest request) {
        try {
            return CompletableFuture.supplyAsync(() -> {
                if (userRepository.findByUsernameOrEmail(request.getUserName(), request.getEmail()).isPresent()) {
                    return ResponseEntity.badRequest().body(
                            Map.of(
                                    "status", HttpStatus.BAD_REQUEST.value(),
                                    "message", "userName hoặc email đã tồn tại"
                            )
                    );
                }

                UserEntity user = new UserEntity();
                user.setUsername(request.getUserName());
                user.setEmail(request.getEmail());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setFirstName(request.getFirstName());
                user.setLastName(request.getLastName());

                userRepository.save(user);
                return ResponseEntity.ok().body(
                        Map.of(
                                "status", 200,
                                "message", "Đăng ký thành công"
                        )
                );

            });
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(
                    Map.of(
                            "status", 500,
                            "message", "Đăng ký thất bại"
                    )
            ));
        }
    }

//    @Override
//    @Async
//    public CompletableFuture<ResponseEntity<?>> Login(LoginRequest request) {
//
//    }
}
