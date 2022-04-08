package com.skilli.game.assignment.securityconfig.exception;

public class JwtFailureException extends RuntimeException{
    public JwtFailureException(String message) {
        super(message);
    }
}
