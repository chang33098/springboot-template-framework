package com.example.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;
import com.example.boot.mapper.SystemRoleMapper;
import com.example.boot.mapper.SystemRoleMenuPermissionRefMapper;
import com.example.boot.mapper.SystemRoleMenuRefMapper;
import com.example.boot.model.role.payload.CreateRolePLO;
import com.example.boot.model.role.payload.ModifyRolePLO;
import com.example.boot.service.SystemRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRoleMenuRef;
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
    private final SystemRoleMenuRefMapper roleMenuRefMapper;
    private final SystemRoleMenuPermissionRefMapper roleMenuPermissionRefMapper;

    @Autowired
    public SystemRoleServiceImpl(SystemRoleMapper roleMapper,
                                 SystemRoleMenuRefMapper roleMenuRefMapper,
                                 SystemRoleMenuPermissionRefMapper roleMenuPermissionRefMapper) {
        this.roleMapper = roleMapper;
        this.roleMenuRefMapper = roleMenuRefMapper;
        this.roleMenuPermissionRefMapper = roleMenuPermissionRefMapper;
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
        Assert.notNull(role, "无法获取ID为[{}]的角色数据", rolePLO.getRoleId());

        BeanUtil.copyProperties(role, rolePLO);
        this.saveOrUpdate(role);
    }

    @Override
    public void delete(Long roleId) {
        //判断当前的角色是否已被使用...

        roleMenuRefMapper.delete(new UpdateWrapper<SystemRoleMenuRef>().lambda().eq(SystemRoleMenuRef::getRoleId, roleId)); //删除角色-菜单的关联
    }
}
