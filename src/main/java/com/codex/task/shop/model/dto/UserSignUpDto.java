package com.codex.task.shop.model.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ToString
public class UserSignUpDto {

    @NotNull
    private String nickname;

    @NotNull
    @Pattern(regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")
    private String email;

    @NotNull
    private String password;

}