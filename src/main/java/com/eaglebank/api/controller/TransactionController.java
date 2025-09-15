package com.eaglebank.api.controller;

import com.eaglebank.api.domain.Transaction;
import com.eaglebank.api.dto.request.TransactionRequest;
import com.eaglebank.api.dto.response.TransactionResponse;
import com.eaglebank.api.service.TransactionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/{accountNumber}/transactions")
public class TransactionController {


    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @PreAuthorize("principal.isAccountOwnerByAccountNumber(#accountNumber)")
    public TransactionResponse createTransaction(@PathVariable String accountNumber, @RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = transactionService.executeTransaction(transactionRequest.toDomain(),accountNumber);
        return new TransactionResponse(transaction);
    }

}
