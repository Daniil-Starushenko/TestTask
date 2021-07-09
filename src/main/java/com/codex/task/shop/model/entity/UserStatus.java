package com.codex.task.shop.model.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import java.util.Arrays;

public enum UserStatus implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }

    public static String[] getAllAuthorities() {
        return Arrays.stream(UserStatus.values()).
                map(UserStatus::getAuthority)
                .toArray(String[]::new);
    }
}
