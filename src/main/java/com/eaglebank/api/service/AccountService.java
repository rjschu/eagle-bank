package com.eaglebank.api.service;

import com.eaglebank.api.domain.Account;
import com.eaglebank.api.domain.AuthUser;
import com.eaglebank.api.entity.AccountEntity;
import com.eaglebank.api.entity.UserEntity;
import com.eaglebank.api.exception.AccountNotFoundException;
import com.eaglebank.api.repository.AccountRepository;
import com.eaglebank.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class AccountService {

    public final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }
    
    public Account createAccount(Account account) {
        Account defaultAccount = new Account.AccountBuilder(account).withBalance(0.00)
                .withAccountNumber(createAccountNumber())
                .build();

        AuthUser principal = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserEntity currentUser = userRepository.findByEmail(principal.getUsername());

        AccountEntity newAccount = accountRepository.save(new AccountEntity(defaultAccount, currentUser));

        return getAccount(newAccount);
    }

    public Account fetchAccount(Long id) {
        return accountRepository.findById(id)
                .map(AccountService::getAccount)
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account %s can not be found",id)));
    }

    public List<Account> fetchAccounts(Long userId) {
        return accountRepository.findAllByUserId(userId).stream().map(AccountService::getAccount).toList();
    }


    public void modifyBalance(Double amount, Long accountId) {
        accountRepository.updateBalance(accountId, amount);
    }

    public static Account getAccount(AccountEntity account) {
        return Account.AccountBuilder.anAccount()
                .withId(account.getId())
                .withName(account.getName())
                .withAccountNumber(account.getAccountNumber())
                .withBalance(account.getBalance())
                .withAccountType(account.getAccountType())
                .withCreatedTimestamp(account.getCreatedTimestamp())
                .withUpdatedTimestamp(account.getUpdatedTimestamp())
                .build();
    }


    private String createAccountNumber() {
        return "01"+String.valueOf(Instant.now().toEpochMilli()).substring(5,11);
    }


    public void deleteAccount(Long accountId) {
        accountRepository.findById(accountId)
                        .ifPresentOrElse(account -> accountRepository.deleteById(accountId),()-> {
                            throw new AccountNotFoundException(String.format("Account %s can not be found",accountId));
                        });
    }
}
