package com.example.boot.springboottemplatestarter.service;

import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import org.springframework.data.domain.Page;

/**
 * Created by ANdady on 2019/7/28.
 */
public interface UserService {

    /**
     * 查询所有后台管理员信息
     *
     * @param pageNo
     * @param limit
     * @return
     */
    Page<SystemUser> findAllUserPage(int pageNo, int limit);

    /**
     * 通过用户(登录账号)获取管理员信息
     *
     * @param username 用户名
     * @return 系统用户
     */
    SystemUser findByUsername(String username);
}
