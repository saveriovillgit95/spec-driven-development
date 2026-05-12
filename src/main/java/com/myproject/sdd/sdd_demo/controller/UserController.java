package com.myproject.sdd.sdd_demo.controller;

import com.myproject.api.UsersApi;
import com.myproject.model.UserDto;
import com.myproject.sdd.sdd_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements the contract defined in openapi.yaml via the generated UsersApi interface.
 * Exception handling (404) is delegated to GlobalExceptionHandler.
 */
@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> getUserById(Integer id) {
        UserDto user = userService.getUser(id);  // throws UserNotFoundException → 404
        return ResponseEntity.ok(user);
    }
}
