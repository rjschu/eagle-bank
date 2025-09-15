package com.eaglebank.api.service;

import com.eaglebank.api.domain.AuthUser;
import com.eaglebank.api.domain.Transaction;
import com.eaglebank.api.entity.AccountEntity;
import com.eaglebank.api.entity.TransactionEntity;
import com.eaglebank.api.enums.TransactionType;
import com.eaglebank.api.repository.AccountRepository;
import com.eaglebank.api.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.BiFunction;

@Service
@Transactional
public class TransactionService {

    public final TransactionRepository transactionRepository;
    public final AccountRepository accountRepository;
    public final AccountService accountService;

    public Map<TransactionType, BiFunction<Transaction, String, Transaction>> transactionExecutors =
            Map.of(TransactionType.DEPOSIT, this::executeDeposit,
                    TransactionType.WITHDRAW, this::executeWithdraw);


    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, AccountService accountService, AccountService accountService1) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService1;
    }

    public Transaction executeTransaction(Transaction transaction, String accountNumber) {
        return transactionExecutors.get(transaction.getType()).apply(transaction, accountNumber);
    }

    public Transaction executeDeposit(Transaction transaction, String accountNumber) {
        AccountEntity byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        TransactionEntity transactionEntity = new TransactionEntity(transaction, byAccountNumber);
        TransactionEntity save = transactionRepository.save(transactionEntity);
        accountService.modifyBalance(save.getAmount()+byAccountNumber.getBalance(), byAccountNumber.getId()  );
        return Transaction.TransactionBuilder.aTransaction()
                .withCurrency(save.getCurrency())
                .withAmount(save.getAmount())
                .withType(save.getType())
                .withReference(save.getReference())
                .withUserId(((AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId())
                .withCreatedTimestamp(save.getCreatedTimestamp())
                .build();
    }

    public Transaction executeWithdraw(Transaction transaction, String accountNumber) {
        return null;
    }
}
