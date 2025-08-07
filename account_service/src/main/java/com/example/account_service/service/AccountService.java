package com.example.account_service.service;

import com.example.account_service.dto.AccountDto;
import com.example.account_service.entity.AccountEntity;
import com.example.account_service.interfaces.IAccount;
import com.example.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccount {
    private final AccountRepository accountRepository;

    @Override
    public List<AccountDto> getAccounts() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        return accountEntities.stream()
                .map(account -> new AccountDto(
                        account.getIdaccount(),
                        account.getUsername(),
                        account.getFirstName(),
                        account.getLastName()))
                .toList();
    }
}
