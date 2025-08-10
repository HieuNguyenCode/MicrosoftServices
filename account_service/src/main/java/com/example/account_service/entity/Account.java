package com.example.account_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @jakarta.validation.constraints.Size(max = 36)
    @ColumnDefault("uuid()")
    @Column(name = "Id_account", nullable = false, length = 36)
    private String idAccount;

    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

}