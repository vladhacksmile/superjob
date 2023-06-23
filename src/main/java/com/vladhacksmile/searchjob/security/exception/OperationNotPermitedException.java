package com.vladhacksmile.searchjob.security.exception;

public class OperationNotPermitedException extends RuntimeException {
    public OperationNotPermitedException(String message) {
        super(message);
    }
}
