package com.codex.task.shop.service.impl;

import com.codex.task.shop.repository.mysql.UserRepository;
import com.codex.task.shop.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;



}
