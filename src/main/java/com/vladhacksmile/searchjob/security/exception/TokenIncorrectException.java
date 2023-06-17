package com.vladhacksmile.searchjob.security.exception;

public class TokenIncorrectException extends RuntimeException {
    public TokenIncorrectException(String message) {
        super(message);
    }
}
