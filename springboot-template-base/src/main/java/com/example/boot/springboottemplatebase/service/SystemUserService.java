package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systemuser.entity.SystemUserEntity;
import com.example.boot.springboottemplatebase.domain.systemuser.value.SecurityGetUserByUsernameVO;
import java.util.Optional;

/**
 * @author chang_
 * @since 2019-11-22
 */
public interface SystemUserService extends IService<SystemUserEntity> {

    Optional<SecurityGetUserByUsernameVO> securityGetUserByUsername(String username);
}
