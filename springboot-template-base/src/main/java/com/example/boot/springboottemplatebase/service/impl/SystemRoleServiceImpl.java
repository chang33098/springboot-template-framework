package com.example.boot.springboottemplatebase.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.boot.springboottemplatebase.domain.systemrole.entity.SystemRoleEntity;
import com.example.boot.springboottemplatebase.domain.systemrole.entity.SystemRoleMenuRefEntity;
import com.example.boot.springboottemplatebase.domain.systemrole.payload.CreateRolePLO;
import com.example.boot.springboottemplatebase.domain.systemrole.payload.ModifyRolePLO;
import com.example.boot.springboottemplatebase.domain.systemuser.entity.SystemUserEntity;
import com.example.boot.springboottemplatebase.mapper.SystemRoleMapper;
import com.example.boot.springboottemplatebase.mapper.SystemRoleMenuPermissionRefMapper;
import com.example.boot.springboottemplatebase.mapper.SystemRoleMenuRefMapper;
import com.example.boot.springboottemplatebase.mapper.SystemUserMapper;
import com.example.boot.springboottemplatebase.service.SystemRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systemrole.entity.SystemRoleMenuPermissionRefEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chang_
 * @since 2019-11-17
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRoleEntity> implements SystemRoleService {

    private final SystemRoleMenuRefMapper roleMenuRefMapper;
    private final SystemRoleMenuPermissionRefMapper roleMenuPermissionRefMapper;
    private final SystemUserMapper systemUserMapper;

    @Autowired
    public SystemRoleServiceImpl(SystemRoleMenuRefMapper roleMenuRefMapper,
                                 SystemRoleMenuPermissionRefMapper roleMenuPermissionRefMapper, SystemUserMapper systemUserMapper) {
        this.roleMenuRefMapper = roleMenuRefMapper;
        this.roleMenuPermissionRefMapper = roleMenuPermissionRefMapper;
        this.systemUserMapper = systemUserMapper;
    }

    @Override
    public void create(CreateRolePLO payload) {
        SystemRoleEntity role = new SystemRoleEntity();
        BeanUtils.copyProperties(payload, role);
        this.save(role);
    }

    @Override
    public void modify(ModifyRolePLO payload) {
        SystemRoleEntity role = this.getById(payload.getRoleId());
        Assert.notNull(role, "无法获取ID为[{}]的角色数据", payload.getRoleId());

        BeanUtil.copyProperties(role, payload);
        this.saveOrUpdate(role);
    }

    @Override
    public void delete(Long roleId) {
        Integer used = systemUserMapper.selectCount(new QueryWrapper<SystemUserEntity>().lambda().eq(SystemUserEntity::getRoleId, roleId));
        Assert.isTrue(used < 1, "该角色已被使用，无法删除");

        roleMenuRefMapper.delete(new UpdateWrapper<SystemRoleMenuRefEntity>().lambda()
                .eq(SystemRoleMenuRefEntity::getRoleId, roleId)); //删除[角色-菜单]的关联数据(逻辑删除)
        roleMenuPermissionRefMapper.delete(new UpdateWrapper<SystemRoleMenuPermissionRefEntity>().lambda()
                .eq(SystemRoleMenuPermissionRefEntity::getRoleId, roleId)); //删除[角色菜单-系统权限]的关联数据(逻辑删除)
        this.removeById(roleId); //删除角色信息(逻辑删除)
    }
}
