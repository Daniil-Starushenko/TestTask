package com.codex.task.shop.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserSignInDto {

    private String email;
    private String password;

}
