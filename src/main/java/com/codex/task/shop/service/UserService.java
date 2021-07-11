package com.codex.task.shop.service;

import com.codex.task.shop.model.dto.UserDto;
import com.codex.task.shop.model.dto.UserSignUpDto;
import com.codex.task.shop.model.entity.User;

public interface UserService {

    User createUser(UserSignUpDto userDto);

    UserDto findUserByEmail(String email);

}
