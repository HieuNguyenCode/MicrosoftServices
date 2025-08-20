package com.example.account_service.controller;

import com.example.account_service.dto.AccountDto;
import com.example.account_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    @Value("${greeting.text}")
    private String greetingText;


    @GetMapping
    public List<AccountDto> getListAccount() {
        return accountService.getAccounts();
    }

    @GetMapping("/greeting")
    public String getGreeting() {
        return greetingText;
    }

}
