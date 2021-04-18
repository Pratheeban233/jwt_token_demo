package com.example.controller;

import com.example.entity.AuthRequest;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialNotFoundException;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(value = "/greet")
    public String getWelcome(){
        return "welcome to jwt demo";
    }

    @PostMapping(value = "/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws CredentialNotFoundException {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (AuthenticationException e) {
           throw new CredentialNotFoundException("Invalid UserName/Password.");
        }

        return jwtUtil.generateToken(authRequest.getUserName());
    }

}
