package com.eaglebank.api.service;

import com.eaglebank.api.domain.AuthUser;
import com.eaglebank.api.entity.UserEntity;
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

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity byEmail = userRepository.findByEmail(email);
        return new AuthUser(byEmail.getEmail(), byEmail.getPassword(), List.of(), byEmail.getId());
    }
}
