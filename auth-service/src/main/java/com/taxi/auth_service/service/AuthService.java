package com.taxi.auth_service.service;

import com.taxi.auth_service.User;
import com.taxi.auth_service.repository.UserRepository;
import com.taxi.auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    private JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder;
    public ResponseEntity<String> registerUser(User user) {
       Boolean exit=userRepository.findAll().stream()
               .anyMatch(isUser->isUser.getUsername().equals(user.getUsername()));
       if(exit){
           return ResponseEntity.badRequest().body("user already exit");
       }
       user.setPassword(passwordEncoder.encode(
               user.getPassword()
       ));
       userRepository.save(user);
       return ResponseEntity.ok("registered successfully" );
    }
    public ResponseEntity<String> login(String username,String passsword) {
        Optional<User> user=userRepository.findByUsername(username);
        if (user.isEmpty()) return ResponseEntity.badRequest().body("user not valid");
        System.out.println(passwordEncoder.encode(passsword));
        System.out.println(user.get().getPassword());
        if(passwordEncoder.matches(passsword,user.get().getPassword())) return ResponseEntity.ok(jwtUtil.generateToken(username,user.get().getRole()));
//user.get().getPassword().equals(passwordEncoder.encode(passsword))
        return ResponseEntity.badRequest().body("empty");

    }



}
