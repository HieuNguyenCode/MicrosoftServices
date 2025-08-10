package com.example.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserJwt {
    private String username;
    private String email;
    private String key;
}
