package com.microservice.hello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping ("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("hello bijen", HttpStatus.OK);
    }


}
