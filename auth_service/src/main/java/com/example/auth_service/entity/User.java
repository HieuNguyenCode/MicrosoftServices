package com.example.auth_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @Size(max = 36)
    @SequenceGenerator(name = "user_id_gen", sequenceName = ")")
    @Column(name = "Id_user", nullable = false, length = 36)
    private String idUser;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 120)
    @NotNull
    @Column(name = "password", nullable = false, length = 120)
    private String password;

    @Size(max = 50)
    @NotNull
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

}