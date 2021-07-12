package com.codex.task.shop.model.dto;

import com.codex.task.shop.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto {

    private Integer id;
    private String nickname;
    private String email;
    private Role status;

    @JsonIgnore
    private String password;

}
