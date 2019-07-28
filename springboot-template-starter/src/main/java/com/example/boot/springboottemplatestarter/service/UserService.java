package com.example.boot.springboottemplatestarter.service;

import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import org.springframework.data.domain.Page;

/**
 * Created by ANdady on 2019/7/28.
 */
public interface UserService {

    /**
     *
     * @param pageNo
     * @param limit
     * @return
     */
    Page<SystemUser> findAllUserPage(int pageNo, int limit);
}
