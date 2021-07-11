package com.codex.task.shop.exception.entity;

import com.codex.task.shop.exception.ApiException;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApiException {

    public EntityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
