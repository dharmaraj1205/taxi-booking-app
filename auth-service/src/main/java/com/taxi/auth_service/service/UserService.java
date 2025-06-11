package com.taxi.auth_service.service;

import com.taxi.auth_service.entity.User;
import com.taxi.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<User> register(User user){

//            Boolean exit=userRepository.findAll().stream()
//                    .anyMatch(isUser->isUser.getUsername().equals(user.getUsername()));
//            if(exit){
//                return ResponseEntity.badRequest().body("user already exit");
//            }
            user.setPassword(passwordEncoder.encode(
                    user.getPassword()
            ));
            User user1=userRepository.save(user);
            return ResponseEntity.ok(user1);
        }


}
