package com.codex.task.shop.model.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;

public enum Role implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }

    public static String[] getAllAuthorities() {
        return Arrays.stream(Role.values()).
                map(Role::getAuthority)
                .toArray(String[]::new);
    }
}
