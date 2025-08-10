package com.example.account_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class AccountDto {
    private String Id_account;
    private String UserName;
    private String FirstName;
    private String LastName;
}
