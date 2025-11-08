package com.dev.Splitwise.exception;

public class UserInvallidPasswordException extends RuntimeException{
    public UserInvallidPasswordException(String message) {
        super(message);
    }
}
