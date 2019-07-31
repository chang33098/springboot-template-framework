package com.example.boot.springboottemplatestarter.service.impl;

import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import com.example.boot.springboottemplatestarter.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/28 15:06
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    public Page<SystemUser> findAllUserPage(int pageNo, int limit) {
        return null;
    }
}
