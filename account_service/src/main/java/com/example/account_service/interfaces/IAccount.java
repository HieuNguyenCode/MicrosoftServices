package com.example.account_service.interfaces;

import com.example.account_service.dto.AccountDto;

import java.util.List;

public interface IAccount {
    List<AccountDto> getAccounts();
}
