package com.example.boot.springboottemplatestarter.service;

import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;

import java.util.List;

/**
 * Created by ANdady on 2019/7/28.
 */
public interface RoleService {

    List<SystemRole> getAllRole();

    /**
     * [spring security] 通过角色ID获取对应的系统菜单
     *
     * @param roleId 角色ID
     * @return 角色菜单
     */
    List<RoleMenuRef> securityGetAllRoleMenuByRoleId(Long roleId);
}
