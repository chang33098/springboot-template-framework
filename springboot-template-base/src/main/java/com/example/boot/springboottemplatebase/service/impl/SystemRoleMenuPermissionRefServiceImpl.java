package com.example.boot.springboottemplatebase.service.impl;

import com.example.boot.springboottemplatebase.mapper.SystemRoleMenuPermissionRefMapper;
import com.example.boot.springboottemplatebase.service.SystemRoleMenuPermissionRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systemrole.persistent.SystemRoleMenuPermissionRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<SystemRoleMenuPermissionRef> securityGetRoleMenuPermissionListByMenuIds(List<Long> menuIdList) {
        return null;
    }
}
