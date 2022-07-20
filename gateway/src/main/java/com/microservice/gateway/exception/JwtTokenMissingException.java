package com.microservice.gateway.exception;

import org.apache.http.auth.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
    private String msg;

    public JwtTokenMissingException(String msg) {
        this.msg = msg;
    }
}
