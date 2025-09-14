package com.eaglebank.api.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class AuthUser extends User {
    private Long id;
    private List<Long> accountIds;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, List<Long> accountIds) {
        super(username, password, authorities);
        this.id = id;
        this.accountIds = accountIds;
    }

    public String getId() {
        return String.format("usr-%d",id);
    }

    public List<Long> getAccountIds() {
        return accountIds;
    }

    public boolean isAccountOwner(Long accountId){
        return accountIds.contains(accountId);
    }
}
