package com.example.boot.springboottemplatestarter.service;

import com.example.boot.springboottemplatedomain.role.payload.*;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by ANdady on 2019/7/28.
 */
public interface RoleService {

    Page<SystemRole> findAllRole(FindAllRolePLOAbstract plo);

    SystemRole getRoleById(Long roleId);

    void createRole(CreateRolePLO plo);

    void modifyRole(Long roleId, ModifyRolePLO plo);

    void deleteRole(Long roleId);

    void createRoleMenu(Long roleId, CreateRoleMenuPLO plo);

    void modifyRoleMenu(Long roleId, Long menuId, ModifyRoleMenuPLO plo);

    void deleteRoleMenu(Long roleId, Long menuId);

    /**
     * [spring security] 通过角色ID获取对应的系统菜单
     *
     * @param roleId 角色ID
     * @return 角色菜单
     */
    List<RoleMenuRef> securityGetAllRoleMenuByRoleId(Long roleId);
}
