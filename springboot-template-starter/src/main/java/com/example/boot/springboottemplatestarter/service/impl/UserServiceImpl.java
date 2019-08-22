package com.example.boot.springboottemplatestarter.service.impl;

import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import com.example.boot.springboottemplatestarter.repository.UserRepository;
import com.example.boot.springboottemplatestarter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/28 15:06
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<SystemUser> findUserTable(int pageNo, int limit) {
        return null;
    }

    @Override
    public Optional<SystemUser> securityGetUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
