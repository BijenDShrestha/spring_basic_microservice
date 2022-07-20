package com.microservice.auth.controller;

import com.microservice.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {
    private JwtUtil jwtUtil;

    @Autowired
    public AuthRestController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> logIn(@RequestBody String username) {
        String token = jwtUtil.generateToken(username);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody String username) {
        // Persist user to some persistence storage
        System.out.println("Info saved...");
        return new ResponseEntity<String>("Registered", HttpStatus.OK);
    }

    @GetMapping("auth/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("hello from auth", HttpStatus.OK);
    }
}
