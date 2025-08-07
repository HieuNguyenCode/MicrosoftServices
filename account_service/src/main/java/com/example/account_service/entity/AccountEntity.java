package com.example.account_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @Column(name = "idaccount", nullable = false, updatable = false)
    private String idaccount;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String firstName;
    private String lastName;

    @PrePersist
    public void generateId() {
        if (this.idaccount == null) {
            this.idaccount = UUID.randomUUID().toString();
        }
    }
}