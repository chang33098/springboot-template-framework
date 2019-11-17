package com.example.boot.service.impl;

import com.example.boot.mapper.SystemRoleMapper;
import com.example.boot.service.SystemRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chang_
 * @since 2019-11-17
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {

    private final SystemRoleMapper roleMapper;

    @Autowired
    public SystemRoleServiceImpl(SystemRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
}
