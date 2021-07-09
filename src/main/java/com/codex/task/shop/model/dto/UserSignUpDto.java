package com.codex.task.shop.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserSignUpDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}