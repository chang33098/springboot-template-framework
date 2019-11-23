package com.example.boot.springboottemplatebase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.boot.springboottemplatebase.mapper.SystemRoleMenuRefMapper;
import com.example.boot.springboottemplatebase.service.SystemRoleMenuRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systemrole.persistent.SystemRoleMenuRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<SystemRoleMenuRef> securityGetRoleMenuListByRoleId(Long roleId) {
        List<SystemRoleMenuRef> roleMenuRefs = this.list(new QueryWrapper<SystemRoleMenuRef>().lambda().eq(SystemRoleMenuRef::getRoleId, roleId));
        return roleMenuRefs;
    }
}
