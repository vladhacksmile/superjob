package com.vladhacksmile.searchjob.controller;

import com.vladhacksmile.searchjob.dto.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleBadRequestException(RuntimeException runtimeException) {
        return new ResponseEntity<Object>(
                new MessageResponse(runtimeException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}