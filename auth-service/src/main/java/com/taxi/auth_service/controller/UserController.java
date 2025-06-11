package com.taxi.auth_service.controller;

import com.taxi.auth_service.entity.User;
import com.taxi.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        return userService.register(user);

    }
}
