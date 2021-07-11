package com.codex.task.shop.model.dto;

import com.codex.task.shop.model.entity.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto {

    private Integer id;
    private String nickname;
    private String email;
    private UserStatus status;

    @JsonIgnore
    private String password;

}
