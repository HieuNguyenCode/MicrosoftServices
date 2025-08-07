package com.example.account_service.controller;

import com.example.account_service.dto.AccountDto;
import com.example.account_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/accounts")
public class AccountController {
    private AccountService accountService;

    public List<AccountDto> getListAccount() {
        return accountService.getAccounts();
    }


}
