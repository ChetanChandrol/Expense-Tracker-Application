package com.dev.Splitwise.exception;

public class UserDoesnotExistException extends RuntimeException{
    public UserDoesnotExistException(String message) {
        super(message);
    }
}
