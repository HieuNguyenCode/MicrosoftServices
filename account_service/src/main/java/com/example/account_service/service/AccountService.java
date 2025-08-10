package com.example.account_service.service;

import com.example.account_service.dto.AccountDto;
import com.example.account_service.entity.Account;
import com.example.account_service.interfaces.IAccount;
import com.example.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccount {

    private final AccountRepository accountRepository;

    public List<AccountDto> getAccounts() {
        List<Account> accountEntities = accountRepository.findAll();
        return accountEntities.stream()
                .map(accountEntity -> new AccountDto(
                        accountEntity.getIdAccount(),
                        accountEntity.getUserName(),
                        accountEntity.getFirstName(),
                        accountEntity.getLastName()
                )).toList();
    }
}
