package com.codex.task.shop.controller;

import com.codex.task.shop.model.dto.UserDto;
import com.codex.task.shop.model.dto.UserSignInDto;
import com.codex.task.shop.model.dto.UserSignUpDto;
import com.codex.task.shop.security.jwt.JwtProvider;
import com.codex.task.shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;


    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signup(@RequestBody UserSignUpDto userSignUp) {
        userService.createUser(userSignUp);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity login(@RequestBody UserSignInDto request) {
        String username = request.getEmail();
        UserDto currentUser = userService.findUserByEmail(username);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
        String token = jwtProvider.createToken(username, currentUser.getStatus());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", currentUser.getStatus().name());

        return ResponseEntity.ok(response);
    }

}
