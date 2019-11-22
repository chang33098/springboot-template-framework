package com.example.boot.springboottemplatebase.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.boot.springboottemplatebase.domain.systemrole.payload.CreateRolePLO;
import com.example.boot.springboottemplatebase.domain.systemrole.payload.ModifyRolePLO;
import com.example.boot.springboottemplatebase.mapper.SystemRoleMapper;
import com.example.boot.springboottemplatebase.mapper.SystemRoleMenuPermissionRefMapper;
import com.example.boot.springboottemplatebase.mapper.SystemRoleMenuRefMapper;
import com.example.boot.springboottemplatebase.service.SystemRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systemrole.persistent.SystemRole;
import com.example.boot.springboottemplatebase.domain.systemrole.persistent.SystemRoleMenuPermissionRef;
import com.example.boot.springboottemplatebase.domain.systemrole.persistent.SystemRoleMenuRef;
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
        // TODO: 2019/11/21 判断当前删除的角色是否已被使用，若已被使用则抛出异常...

        roleMenuRefMapper.delete(new UpdateWrapper<SystemRoleMenuRef>().lambda()
                .eq(SystemRoleMenuRef::getRoleId, roleId)); //删除[角色-菜单]的关联数据(逻辑删除)
        roleMenuPermissionRefMapper.delete(new UpdateWrapper<SystemRoleMenuPermissionRef>().lambda()
                .eq(SystemRoleMenuPermissionRef::getRoleId, roleId)); //删除[角色菜单-系统权限]的关联数据(逻辑删除)
        this.removeById(roleId); //删除角色信息(逻辑删除)
    }
}
