package com.codex.task.shop.service.impl;

import com.codex.task.shop.exception.auth.AuthException;
import com.codex.task.shop.exception.entity.EntityNotFoundException;
import com.codex.task.shop.model.dto.UserDto;
import com.codex.task.shop.model.dto.UserSignUpDto;
import com.codex.task.shop.model.entity.User;
import com.codex.task.shop.model.entity.Role;
import com.codex.task.shop.repository.mysql.UserRepository;
import com.codex.task.shop.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    ModelMapper modelMapper;

    PasswordEncoder encoder;


    @Override
    public User createUser(UserSignUpDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            log.info("user with email {} is exist", userDto.getEmail());
            throw new AuthException(HttpStatus.BAD_REQUEST,
                    "the email is already taken: " + userDto.getEmail());
        }
        User user =  modelMapper.map(userDto, User.class);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setStatus(Role.USER);
        log.info("saving new user");
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findUserByEmail(String email) {
        User soughtUser = userRepository.findUserByEmail(email)
                .orElseThrow((() -> new EntityNotFoundException("entity with email was not found: "
                        + email)));
        return modelMapper.map(soughtUser, UserDto.class);
    }
}
