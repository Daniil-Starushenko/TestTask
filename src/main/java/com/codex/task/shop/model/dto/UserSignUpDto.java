package com.codex.task.shop.model.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class UserSignUpDto {

    @NotNull
    private String nickname;

    @NotNull
    private String email;

    @NotNull
    private String password;

}