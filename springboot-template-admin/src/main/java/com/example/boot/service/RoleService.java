package com.example.boot.service;

import com.example.boot.model.role.payload.*;
import com.example.boot.model.role.response.GetRoleListRO;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuPermissionRef;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by ANdady on 2019/7/28.
 */
public interface RoleService {

    Page<SystemRole> findRoleTable(FindRoleTablePLO plo);

    SystemRole getRoleById(Long roleId);

    void createRole(CreateRolePLO plo);

    void modifyRole(Long roleId, ModifyRolePLO plo);

    void deleteRole(Long roleId);

    List<RoleMenuRef> getRoleRootMenuListByRoleId(Long roleId);

    RoleMenuRef getRoleMenuByRoleIdAndMenuId(Long roleId, Long menuId);

    List<RoleMenuPermissionRef> getRoleMenuPermissionListByMenuId(Long menuId);

    void createRoleRootMenu(Long roleId, CreateRoleRootMenuPLO plo);

    void createRoleSubMenu(Long roleId, Long parentId, CreateRoleSubMenuPLO plo);

    void modifyRoleRootMenu(Long roleId, Long menuId, ModifyRoleRootMenuPLO plo);

    void modifyRoleSubMenu(Long roleId, Long parentId, Long menuId, ModifyRoleSubMenuPLO plo);

    void deleteRoleMenu(Long roleId, Long menuId);

    void deleteRoleMenuPermissionByPagePermissionId(Long pagePermissionId);

    List<GetRoleListRO> getRoleList();
}
