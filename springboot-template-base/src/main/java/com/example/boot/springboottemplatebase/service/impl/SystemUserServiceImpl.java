package com.example.boot.springboottemplatebase.service.impl;

import com.example.boot.springboottemplatebase.mapper.SystemUserMapper;
import com.example.boot.springboottemplatebase.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systemuser.persistent.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final SystemUserMapper userMapper;

    @Autowired
    public SystemUserServiceImpl(SystemUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<SystemUser> securityGetUserByUsername(String username) {
        return null;
    }
}
