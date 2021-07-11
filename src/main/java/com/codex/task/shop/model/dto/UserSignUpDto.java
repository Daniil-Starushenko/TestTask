package com.codex.task.shop.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserSignUpDto {

    private String nickname;
    private String email;
    private String password;

}