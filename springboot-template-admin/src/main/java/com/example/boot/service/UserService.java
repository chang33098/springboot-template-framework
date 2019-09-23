package com.example.boot.service;

import com.example.boot.model.user.payload.CreateUserPLO;
import com.example.boot.model.user.payload.FindUserTablePLO;
import com.example.boot.model.user.payload.ModifyUserPLO;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import org.springframework.data.domain.Page;

/**
 * Created by ANdady on 2019/7/28.
 */
public interface UserService {

    Page<SystemUser> findUserTable(FindUserTablePLO plo);

    SystemUser getUserById(Long userId);

    void createUser(CreateUserPLO plo);

    void modifyUser(Long userId, ModifyUserPLO plo);

    void deleteUser(Long userId);
}
