package com.example.boot.springboottemplatebase.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systempermission.payload.CreatePermissionPLO;
import com.example.boot.springboottemplatebase.domain.systempermission.payload.ModifyPermissionPLO;
import com.example.boot.springboottemplatebase.mapper.SystemPagePermissionRefMapper;
import com.example.boot.springboottemplatebase.mapper.SystemPermissionMapper;
import com.example.boot.springboottemplatebase.service.SystemPermissionService;
import com.example.boot.springboottemplatebase.domain.systempage.entity.SystemPagePermissionRef;
import com.example.boot.springboottemplatebase.domain.systempermission.entity.SystemPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chang_
 * @since 2019-11-16
 */
@Service
@Transactional
public class SystemPermissionServiceImpl extends ServiceImpl<SystemPermissionMapper, SystemPermission> implements SystemPermissionService {

    private final SystemPagePermissionRefMapper pagePermissionRefMapper;

    @Autowired
    public SystemPermissionServiceImpl(SystemPagePermissionRefMapper pagePermissionRefMapper) {
        this.pagePermissionRefMapper = pagePermissionRefMapper;
    }

    @Override
    public void create(CreatePermissionPLO payload) {
        SystemPermission permission = new SystemPermission();
        BeanUtil.copyProperties(payload, permission);
        this.save(permission);
    }

    @Override
    public void modify(ModifyPermissionPLO payload) {
        SystemPermission permission = this.getById(payload.getPermissionId());
        Assert.notNull(permission, "无法获取ID[{}]的权限信息", payload.getPermissionId());

        BeanUtil.copyProperties(payload, permission);
        this.saveOrUpdate(permission);
    }

    @Override
    public void delete(Long permissionId) {
        SystemPermission permission = this.getById(permissionId);
        Assert.notNull(permission, "无法获取ID[{}]的权限信息", permissionId);

        int usedCount = pagePermissionRefMapper.selectCount(new QueryWrapper<SystemPagePermissionRef>().lambda().eq(SystemPagePermissionRef::getPermissionId, permissionId));
        Assert.isFalse(usedCount > 0, "权限[{}]已被使用，无法删除", permission.getPermissionName());

        this.removeById(permissionId); //删除权限
    }
}
