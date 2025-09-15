package com.eaglebank.api.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class AuthUser extends User {
    private Long id;
    private List<Account> accountIds;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, List<Account> accountIds) {
        super(username, password, authorities);
        this.id = id;
        this.accountIds = accountIds;
    }

    public String getId() {
        return String.format("usr-%d",id);
    }

    public List<Account> getAccountIds() {
        return accountIds;
    }

    public boolean isAccountOwnerByAccountId(Long accountId){
        return accountIds.stream().anyMatch(account -> account.getId().equals(accountId));
    }

    public boolean isAccountOwnerByAccountNumber(String accountNumber){
        return accountIds.stream().anyMatch(account -> account.getAccountNumber().equals(accountNumber));
    }
}
