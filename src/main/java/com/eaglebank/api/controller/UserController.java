package com.eaglebank.api.controller;

import com.eaglebank.api.domain.Address;
import com.eaglebank.api.domain.User;
import com.eaglebank.api.dto.request.CreateUserRequest;
import com.eaglebank.api.dto.request.UpdateUserRequest;
import com.eaglebank.api.dto.response.AddressResponse;
import com.eaglebank.api.dto.response.UserResponse;
import com.eaglebank.api.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        User user = userService.createUser(createUserRequest.toDomain());
        return toUserResponse(user);
    }

    @GetMapping("{userId}")
    @PreAuthorize("#userId == principal.id")
    public UserResponse fetchUser(@PathVariable @Pattern(regexp = "^usr-[A-Za-z0-9]+$") String userId) {
        User user = userService.fetchUser(userId);
        return toUserResponse(user);
    }

    @PatchMapping("{userId}")
    @PreAuthorize("#userId == principal.id")
    public UserResponse updateUser(@PathVariable @Pattern(regexp = "^usr-[A-Za-z0-9]+$") String userId, @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        User user = userService.updateUser(updateUserRequest.toDomain());
        return toUserResponse(user);
    }

    @DeleteMapping("{userId}")
    @PreAuthorize("#userId == principal.id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable @Pattern(regexp = "^usr-[A-Za-z0-9]+$") String userId) {
        userService.deleteUser(userId);
    }

    private UserResponse toUserResponse(User user) {
        Address address = user.getAddress();
        AddressResponse addressResponse = new AddressResponse(address.getLine1(), address.getLine2(), address.getLine3(), address.getTown(), address.getCounty(), address.getPostcode());
        return new UserResponse(String.valueOf(user.getId()), user.getName(), user.getEmail(), user.getPhoneNumber(), addressResponse, user.getCreatedTimestamp(), user.getUpdatedTimestamp());
    }
}
