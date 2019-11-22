package com.example.boot.springboottemplatebase.service.impl;

import com.example.boot.springboottemplatebase.mapper.SystemRoleMenuPermissionRefMapper;
import com.example.boot.springboottemplatebase.service.SystemRoleMenuPermissionRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRoleMenuPermissionRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chang_
 * @since 2019-11-17
 */
@Service
public class SystemRoleMenuPermissionRefServiceImpl extends ServiceImpl<SystemRoleMenuPermissionRefMapper, SystemRoleMenuPermissionRef> implements SystemRoleMenuPermissionRefService {

    private final SystemRoleMenuPermissionRefMapper roleMenuPermissionRefMapper;

    @Autowired
    public SystemRoleMenuPermissionRefServiceImpl(SystemRoleMenuPermissionRefMapper roleMenuPermissionRefMapper) {
        this.roleMenuPermissionRefMapper = roleMenuPermissionRefMapper;
    }
}
