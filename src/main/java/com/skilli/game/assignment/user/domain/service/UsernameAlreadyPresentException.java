package com.skilli.game.assignment.user.domain.service;

public class UsernameAlreadyPresentException extends Exception{
    public UsernameAlreadyPresentException(String message) {
        super(message);
    }
}
