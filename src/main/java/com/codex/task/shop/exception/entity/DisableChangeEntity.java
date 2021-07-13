package com.codex.task.shop.exception.entity;

import com.codex.task.shop.exception.ApiException;
import org.springframework.http.HttpStatus;

public class DisableChangeEntity extends ApiException {

    public DisableChangeEntity(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}