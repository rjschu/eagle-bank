package com.eaglebank.api.service;

import com.eaglebank.api.domain.Account;
import com.eaglebank.api.domain.AuthUser;
import com.eaglebank.api.entity.AccountEntity;
import com.eaglebank.api.entity.UserEntity;
import com.eaglebank.api.enums.AccountType;
import com.eaglebank.api.enums.UserRole;
import com.eaglebank.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;
    private final AccountService accountService;

    public CustomUserDetailsService(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity byEmail = userRepository.findByEmail(email);
        List<Long> accountIds = accountService.fetchAccounts(byEmail.getId()).stream().map(Account::getId).toList();
        return new AuthUser(byEmail.getEmail(), byEmail.getPassword(), List.of(), byEmail.getId(), accountIds);
    }
}