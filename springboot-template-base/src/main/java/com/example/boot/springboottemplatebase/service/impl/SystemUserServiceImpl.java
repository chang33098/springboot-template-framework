package com.example.boot.springboottemplatebase.service.impl;

import com.example.boot.springboottemplatebase.domain.systemuser.value.SecurityGetUserByUsernameVO;
import com.example.boot.springboottemplatebase.mapper.SystemUserMapper;
import com.example.boot.springboottemplatebase.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systemuser.persistent.SystemUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author chang_
 * @since 2019-11-22
 */
@Service
@Transactional
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    @Override
    public Optional<SecurityGetUserByUsernameVO> securityGetUserByUsername(String username) {
        return this.getBaseMapper().securityGetUserByUsername(username);
    }
}
