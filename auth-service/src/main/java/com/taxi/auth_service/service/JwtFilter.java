package com.taxi.auth_service.service;

import com.taxi.auth_service.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

public class JwtFilter extends OncePerRequestFilter {
    private JwtUtil jwtUtil;
    private UserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil,UserDetailsService userDetailsService){
        this.jwtUtil=jwtUtil;
        this.userDetailsService=userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String autheader=request.getHeader("Authorization");
        Enumeration<String> headerNames=request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }
        System.out.println("runnig "+ request.getHeader("Authorization"));
        if(autheader!=null && autheader.startsWith("Bearer ")){
            System.out.println("jwt  bearer");
            String token=autheader.substring(7).trim();
            String role = jwtUtil.extractRole(token);
            String username=jwtUtil.extractUsername(token);
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                System.out.println("jwt  seutityvauthentication");

                UserDetails userDetails=userDetailsService.loadUserByUsername(username);
                if(jwtUtil.validateToken(token,username)){
                    System.out.println("jwt   user authority"+ userDetails.getAuthorities());
                    UsernamePasswordAuthenticationToken authToken=
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }
            }
        }
        filterChain.doFilter(request,response);

    }
}
