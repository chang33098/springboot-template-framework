package com.example.boot.service.impl;

import cn.hutool.core.lang.Assert;
import com.example.boot.mapper.SystemRoleMapper;
import com.example.boot.model.role.payload.CreateRolePLO;
import com.example.boot.model.role.payload.ModifyRolePLO;
import com.example.boot.service.SystemRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import org.springframework.beans.BeanUtils;
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

    @Override
    public void create(CreateRolePLO rolePLO) {
        SystemRole role = new SystemRole();
        BeanUtils.copyProperties(rolePLO, role);
        this.save(role);
    }

    @Override
    public void modify(ModifyRolePLO rolePLO) {
        SystemRole role = this.getById(rolePLO.getRoleId());
        Assert.notNull(role, "");
    }

    @Override
    public void delete(Long roleId) {

    }
}
