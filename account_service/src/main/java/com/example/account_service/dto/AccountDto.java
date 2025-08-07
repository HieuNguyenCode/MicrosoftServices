package com.example.account_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AccountDto {
    private String idaccount;
    private String username;
    private String firstName;
    private String lastName;
}
