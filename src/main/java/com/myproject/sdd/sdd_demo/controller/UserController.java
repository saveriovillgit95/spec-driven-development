package com.myproject.sdd.sdd_demo.controller;

import com.myproject.api.UsersApi;
import com.myproject.model.UserDto;
import com.myproject.sdd.sdd_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> getUserById(Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
