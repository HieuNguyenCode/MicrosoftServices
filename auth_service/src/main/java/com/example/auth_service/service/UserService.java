package com.example.auth_service.service;

import com.example.auth_service.dto.LoginRequest;
import com.example.auth_service.dto.RefreshRequest;
import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.entity.Refreshtoken;
import com.example.auth_service.entity.User;
import com.example.auth_service.interfaces.IUser;
import com.example.auth_service.repository.RefreshTokenRepository;
import com.example.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserService implements IUser {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> Register(RegisterRequest request) {
        try {
            return CompletableFuture.supplyAsync(() -> {
                if (userRepository.findByUserNameOrEmail(request.getUserName(), request.getEmail()).isPresent()) {
                    return ResponseEntity.badRequest().body(
                            Map.of(
                                    "status", HttpStatus.BAD_REQUEST.value(),
                                    "message", "userName hoặc email đã tồn tại"
                            )
                    );
                }

                User user = new User();
                user.setUserName(request.getUserName());
                user.setEmail(request.getEmail());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setFirstName(request.getFirstName());
                user.setLastName(request.getLastName());

                userRepository.save(user);
                return ResponseEntity.ok().body(
                        Map.of(
                                "status", HttpStatus.OK.value(),
                                "message", "Đăng ký thành công"
                        )
                );

            });
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(
                    Map.of(
                            "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "message", "Đăng ký thất bại"
                    )
            ));
        }
    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> Login(LoginRequest request) {
        try {
            return CompletableFuture.supplyAsync(() -> {
                User user = userRepository.findByUserName(request.getUsername())
                        .orElse(null);
                if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                    return ResponseEntity.badRequest().body(
                            Map.of(
                                    "status", HttpStatus.UNAUTHORIZED.value(),
                                    "message", "Tài khoản hoặc mật khẩu không đúng"
                            )
                    );
                }

                String keyAccessToken = UUID.randomUUID().toString();
                String keyRefreshToken = UUID.randomUUID().toString();
                String accessToken = jwtService.generateAccessToken(
                        Map.of(
                                "username", user.getUserName(),
                                "email", user.getEmail(),
                                "key", keyAccessToken
                        ),
                        user.getUserName()
                );

                if (accessToken == null || accessToken.isEmpty()) {
                    return ResponseEntity.badRequest().body(
                            Map.of(
                                    "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                    "message", "Lỗi khi tạo access token"
                            )
                    );
                }
                Refreshtoken refreshTokenNew = new Refreshtoken();
                refreshTokenNew.setKeyaccesstoken(keyAccessToken);
                refreshTokenNew.setKeyrefreshtoken(keyRefreshToken);
                refreshTokenNew.setIdUser(user);
                refreshTokenNew.setExpiryDate(LocalDateTime.now().plusHours(1).toInstant(java.time.ZoneOffset.UTC));
                refreshTokenRepository.save(refreshTokenNew);

                return ResponseEntity.ok().body(
                        Map.of(
                                "status", HttpStatus.OK.value(),
                                "message", "Đăng nhập thành công",
                                "data", Map.of(
                                        "accessToken", accessToken,
                                        "refreshToken", keyRefreshToken
                                )
                        )
                );
            });
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(
                    Map.of(
                            "status", 500,
                            "message", "Đăng nhập thất bại"
                    )
            ));
        }
    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> Refresh(RefreshRequest request, String token) {
        try {
            return CompletableFuture.supplyAsync(() -> {
                var claims = jwtService.parseTokenAllowExpired(token);
                if (claims == null || claims.getKey() == null) {
                    return ResponseEntity.badRequest().body(
                            Map.of(
                                    "status", HttpStatus.UNAUTHORIZED.value(),
                                    "message", "Token không hợp lệ"
                            )
                    );
                }

                Refreshtoken refreshToken = refreshTokenRepository.findByKeyrefreshtokenOrKeyaccesstoken(request.getRefreshToken(), claims.getKey())
                        .orElse(null);
                if (refreshToken == null) {
                    return ResponseEntity.badRequest().body(
                            Map.of(
                                    "status", HttpStatus.UNAUTHORIZED.value(),
                                    "message", "Refresh token không hợp lệ"
                            )
                    );
                }

                String keyAccessToken = UUID.randomUUID().toString();
                String keyRefreshToken = UUID.randomUUID().toString();
                String accessToken = jwtService.generateAccessToken(
                        Map.of(
                                "username", claims.getUsername(),
                                "email", claims.getEmail(),
                                "key", keyAccessToken
                        ),
                        claims.getUsername()
                );

                if (accessToken == null || accessToken.isEmpty()) {
                    return ResponseEntity.badRequest().body(
                            Map.of(
                                    "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                    "message", "Lỗi khi tạo access token"
                            )
                    );
                }

                refreshToken.setKeyrefreshtoken(keyRefreshToken);
                refreshToken.setKeyaccesstoken(keyAccessToken);

                refreshTokenRepository.save(refreshToken);

                return ResponseEntity.ok().body(
                        Map.of(
                                "status", HttpStatus.OK.value(),
                                "message", "Làm mới token thành công",
                                "data", Map.of(
                                        "accessToken", accessToken,
                                        "refreshToken", keyRefreshToken
                                )
                        )
                );
            });
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(
                    Map.of(
                            "status", 500,
                            "message", "Làm mới token thất bại"
                    )
            ));
        }
    }
}
