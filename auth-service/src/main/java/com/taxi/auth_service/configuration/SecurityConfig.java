package com.taxi.auth_service.configuration;

import com.taxi.auth_service.repository.UserRepository;
import com.taxi.auth_service.service.CustomerDetailService;
import com.taxi.auth_service.service.JwtFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig{
    @Autowired
    private CustomerDetailService customerDetailService;

    private JwtFilter jwtfilter;

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        auth->auth.requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/admin/**").hasAnyAuthority("CUSTOMER","ADMIN")
                                .requestMatchers("/driver/**").hasAnyAuthority("DRIVER,'ADMIN")
                                .anyRequest().authenticated()
                ).addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
               return httpSecurity.build();
    }

    public PasswordEncoder  passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
