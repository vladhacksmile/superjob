package com.vladhacksmile.searchjob.security.exception;

public class UserNotAuthException extends RuntimeException {
    public UserNotAuthException(String message) {
        super(message);
    }
}
