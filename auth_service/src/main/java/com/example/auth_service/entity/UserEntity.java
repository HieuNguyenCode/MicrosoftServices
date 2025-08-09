package com.example.auth_service.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdUser", columnDefinition = "CHAR(36)")
    private UUID idUser;

    @Column(name = "user_name", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(120)", nullable = false)
    private String password;

    @Column(name = "email", columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", columnDefinition = "VARCHAR(50)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR(50)")
    private String lastName;
}