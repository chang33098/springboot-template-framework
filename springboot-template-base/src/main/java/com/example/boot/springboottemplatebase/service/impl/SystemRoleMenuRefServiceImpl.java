package com.example.boot.springboottemplatebase.service.impl;

import com.example.boot.springboottemplatebase.mapper.SystemRoleMenuRefMapper;
import com.example.boot.springboottemplatebase.service.SystemRoleMenuRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRoleMenuRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chang_
 * @since 2019-11-17
 */
@Service
public class SystemRoleMenuRefServiceImpl extends ServiceImpl<SystemRoleMenuRefMapper, SystemRoleMenuRef> implements SystemRoleMenuRefService {

    private final SystemRoleMenuRefMapper roleMenuRefMapper;

    @Autowired
    public SystemRoleMenuRefServiceImpl(SystemRoleMenuRefMapper roleMenuRefMapper) {
        this.roleMenuRefMapper = roleMenuRefMapper;
    }
}
