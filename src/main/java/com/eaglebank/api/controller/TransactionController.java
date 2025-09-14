package com.eaglebank.api.controller;

import com.eaglebank.api.dto.request.TransactionRequest;
import com.eaglebank.api.dto.response.TransactionResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts/{accountNumber}/transactions")
public class TransactionController {

    @PostMapping
    public TransactionResponse createTransaction(TransactionRequest transactionRequest){
        return null;
    }

}
