package com.eaglebank.api.controller;

import com.eaglebank.api.domain.Account;
import com.eaglebank.api.dto.request.CreateAccountRequest;
import com.eaglebank.api.dto.response.AccountResponse;
import com.eaglebank.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponse createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest){
        Account account = accountService.createAccount(createAccountRequest.toDomain());
        return new AccountResponse(account);
    }

    @GetMapping("/{accountId}")
    @PreAuthorize("principal.isAccountOwnerByAccountId(#accountId)")
    public AccountResponse getAccount(@PathVariable Long accountId){
        Account account = accountService.fetchAccount(accountId);
        return new AccountResponse(account);
    }

    @DeleteMapping("/{accountId}")
    @PreAuthorize("principal.isAccountOwnerByAccountId(#accountId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long accountId){
      accountService.deleteAccount(accountId);
    }
    
}
