package com.eaglebank.api.service;

import com.eaglebank.api.domain.Address;
import com.eaglebank.api.domain.User;
import com.eaglebank.api.entity.AddressEntity;
import com.eaglebank.api.entity.UserEntity;
import com.eaglebank.api.exception.UserNotFoundException;
import com.eaglebank.api.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }

    public User createUser(User newUser) {
        UserEntity createdUser = userRepository.save(new UserEntity(newUser, passwordEncoder.encode(newUser.getPassword())));
        return createUser(createdUser);
    }

    public User fetchUser(String userId) {
        Long strippedUserId = getStrippedUserId(userId);
        return userRepository.findById(strippedUserId)
                .map(this::createUser)
                .orElseThrow(()-> new UserNotFoundException(String.format("User with id %s not found", userId)));
    }


    public User updateUser(User user) {

        return userRepository.findById(user.getId())
                .map(u -> u.patch(user))
                .map(userRepository::save)
                .map(this::createUser)
                .orElseThrow();
    }


    public void deleteUser(String userId) {
        Long strippedUserId = getStrippedUserId(userId);
        userRepository.findById(strippedUserId)
                        .ifPresentOrElse(userRepository::delete,()-> {
                            throw new UserNotFoundException(String.format("User with id %s not found", userId));
                        });
    }


    private User createUser(UserEntity createdUser) {
        AddressEntity addressEntity = createdUser.getAddress();
        Address address = new Address(addressEntity.getLine1(), addressEntity.getLine2(), addressEntity.getLine3(),
                addressEntity.getTown(), addressEntity.getCounty(), addressEntity.getPostcode());
            return User.UserBuilder.builder()
                    .withId(createdUser.getId())
                    .withName(createdUser.getName())
                    .withEmail(createdUser.getEmail())
                    .withPassword(createdUser.getPassword())
                    .withPhoneNumber(createdUser.getPhoneNumber())
                    .withAddress(address)
                    .withCreatedTimestamp(createdUser.getCreatedTimestamp())
                    .withUpdatedTimestamp(createdUser.getUpdatedTimestamp())
                    .build();
    }

    private static Long getStrippedUserId(String userId) {
        return Long.valueOf(userId.replace("usr-", ""));
    }

}
