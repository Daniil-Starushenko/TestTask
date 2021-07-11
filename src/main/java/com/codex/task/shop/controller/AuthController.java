package com.codex.task.shop.controller;

import com.codex.task.shop.model.dto.UserSignUpDto;
import com.codex.task.shop.model.entity.User;
import com.codex.task.shop.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private ModelMapper modelMapper;


    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signup(@RequestBody UserSignUpDto userSignUp) {
        User user = modelMapper.map(userSignUp, User.class);
        userService.createUser(user);
    }

}
