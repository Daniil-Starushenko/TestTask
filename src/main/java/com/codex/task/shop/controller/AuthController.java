package com.codex.task.shop.controller;

import com.codex.task.shop.service.UserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private UserService userService;
    private ModelMapper modelMapper;


    @PostMapping("/signup")
    public String home(){
        return "Hello World!";
    }
}
