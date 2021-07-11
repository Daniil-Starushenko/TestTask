package com.codex.task.shop.exception.auth;

import com.codex.task.shop.exception.ApiException;
import org.springframework.http.HttpStatus;

public class AuthException extends ApiException {

    public AuthException(HttpStatus errorCode, String message) {
        super(errorCode, message);
    }

}
