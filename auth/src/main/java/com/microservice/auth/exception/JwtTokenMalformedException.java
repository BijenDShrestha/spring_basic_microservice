package com.microservice.auth.exception;

import org.apache.http.auth.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {
    private String msg;

    public JwtTokenMalformedException(String msg) {
        this.msg = msg;
    }
}
