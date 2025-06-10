package com.taxi.auth_service;

import com.taxi.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Authcontroller {

    @Autowired
    private AuthService authService;
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestParam String username,@RequestParam String password){
        return authService.login(username,password);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(User user){
        return authService.registerUser(user);
    }
}
