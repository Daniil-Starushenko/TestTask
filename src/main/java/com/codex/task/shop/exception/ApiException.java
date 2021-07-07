package com.codex.task.shop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public abstract class ApiException extends RuntimeException {

    HttpStatus status;
    private LocalDateTime timestamp;

    public ApiException(HttpStatus errorCode, String message) {
        super(message);
        this.status = errorCode;
        this.timestamp = LocalDateTime.now();
    }

}