package com.codex.task.shop.service.impl;

import com.codex.task.shop.exception.auth.AuthException;
import com.codex.task.shop.model.entity.User;
import com.codex.task.shop.model.entity.UserStatus;
import com.codex.task.shop.repository.mysql.UserRepository;
import com.codex.task.shop.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;


    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AuthException(HttpStatus.BAD_REQUEST,
                    "the email is already taken: " + user.getEmail());
        }
        user.setStatus(UserStatus.USER);
        return userRepository.save(user);
    }
}
