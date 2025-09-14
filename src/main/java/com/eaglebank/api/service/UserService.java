package com.eaglebank.api.service;

import com.eaglebank.api.domain.Address;
import com.eaglebank.api.domain.User;
import com.eaglebank.api.entity.AddressEntity;
import com.eaglebank.api.entity.UserEntity;
import com.eaglebank.api.exception.UserNotFoundException;
import com.eaglebank.api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User newUser) {
        UserEntity createdUser = userRepository.save(new UserEntity(newUser, passwordEncoder.encode(newUser.getPassword())));
        return createUser(createdUser);
    }

    public User fetchUser(String userId) {
        Long strippedUserId = Long.valueOf(userId.replace("usr-", ""));
        return userRepository.findById(strippedUserId)
                .map(this::createUser)
                .orElseThrow(()-> new UserNotFoundException(String.format("User with id %s not found", userId)));
    }


    private User createUser(UserEntity createdUser) {
        AddressEntity addressEntity = createdUser.getAddress();
        Address address = new Address(addressEntity.getLine1(), addressEntity.getLine2(), addressEntity.getLine3(),
                addressEntity.getTown(), addressEntity.getCounty(), addressEntity.getPostcode());
        return new User(createdUser.getId(), createdUser.getName(), createdUser.getEmail(), createdUser.getPhoneNumber(),
                address, createdUser.getCreatedTimestamp(), createdUser.getUpdatedTimestamp(), createdUser.getPassword());
    }
}
