package com.example.boot.springboottemplatestarter.service;

import com.example.boot.springboottemplatedomain.user.payload.FindUserTablePLO;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Created by ANdady on 2019/7/28.
 */
public interface UserService {

    Page<SystemUser> findUserTable(FindUserTablePLO plo);

    /**
     * [spring security] 通过用户(登录账号)获取管理员信息
     *
     * @param username 用户名
     * @return 系统用户
     */
    Optional<SystemUser> securityGetUserByUsername(String username);
}
