package com.example.account_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @Column(name = "IdAccount", columnDefinition = "CHAR(36)")
    private UUID IdAccount;

    @Column(name = "user_name", nullable = false, unique = true, length = 50)
    private String UserName;

    @Column(name = "first_name", length = 50)
    private String FirstName;

    @Column(name = "last_name", length = 50)
    private String LastName;

    @Column(name = "IdDepartment", columnDefinition = "CHAR(36)")
    private UUID IdDepartment;

    @PrePersist
    protected void onCreate() {
        this.IdAccount = UUID.randomUUID();
    }
}