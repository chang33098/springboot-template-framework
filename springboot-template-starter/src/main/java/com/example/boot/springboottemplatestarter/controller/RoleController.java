package com.example.boot.springboottemplatestarter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.role.payload.*;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatedomain.role.response.FindRoleTableRO;
import com.example.boot.springboottemplatedomain.role.response.GetRoleMenuRO;
import com.example.boot.springboottemplatedomain.role.response.ModifyRoleRO;
import com.example.boot.springboottemplatedomain.role.response.RoleMenuTreeRO;
import com.example.boot.springboottemplatestarter.response.ResponseBodyBean;
import com.example.boot.springboottemplatestarter.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/23 22:32
 */
@Slf4j
@Controller
@RequestMapping(value = "/system/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String role() {
        return "system/role/role_list";
    }

    @GetMapping(value = "table")
    @ResponseBody
    public ResponseBodyBean<Page<FindRoleTableRO>> findRoleTable(FindRoleTablePLO plo) {
        Page<SystemRole> rolePage = roleService.findRoleTable(plo);

        List<FindRoleTableRO> roleROS = FindRoleTableRO.createFindAllRoleROS(rolePage.getContent());
        Page<FindRoleTableRO> roleROPage = new PageImpl<>(roleROS,
                rolePage.getPageable(), rolePage.getTotalElements());

        return ResponseBodyBean.ofSuccess(roleROPage);
    }

    @GetMapping(value = "create")
    public String createRole() {
        return "system/role/role_create";
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean createRole(@RequestBody @Valid CreateRolePLO plo) {
        roleService.createRole(plo);
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "modify/{role_id}")
    public String modifyRole(@PathVariable(value = "role_id") Long roleId, Model model) {
        SystemRole role = roleService.getRoleById(roleId);

        ModifyRoleRO roleRO = new ModifyRoleRO();
        BeanUtil.copyProperties(role, roleRO);

        model.addAttribute("role", roleRO);

        return "system/role/role_modify";
    }

    @PutMapping(value = "modify/{role_id}")
    @ResponseBody
    public ResponseBodyBean modifyRole(@PathVariable(value = "role_id") Long roleId,
                                       @RequestBody @Valid ModifyRolePLO plo) {
        roleService.modifyRole(roleId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @DeleteMapping(value = "delete/{role_id}")
    @ResponseBody
    public ResponseBodyBean deleteRole(@PathVariable(value = "role_id") Long roleId) {
        roleService.deleteRole(roleId);
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "{role_id}/role_menu")
    public String roleMenu(@PathVariable(value = "role_id") Long roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "system/role/role_menu";
    }

    @GetMapping(value = "{role_id}/get_role_menu_tree")
    @ResponseBody
    public ResponseBodyBean<List<RoleMenuTreeRO>> getRoleMenuTree(@PathVariable(value = "role_id") Long roleId) {
        List<RoleMenuRef> menuRefs = roleService.getRoleRootMenuListByRoleId(roleId);
        List<RoleMenuTreeRO> menuROS = menuRefs.stream().map(menuRef -> {
            RoleMenuTreeRO menuRO = new RoleMenuTreeRO();
            menuRO.transferTreeNode(menuRef);
            return menuRO;
        }).collect(Collectors.toList());

        return ResponseBodyBean.ofSuccess(menuROS);
    }

    @RequestMapping(value = "{role_id}/get_role_menu/{menu_id}")
    @ResponseBody
    public ResponseBodyBean getRoleMenu(@PathVariable(value = "role_id") Long roleId,
                                           @PathVariable(value = "menu_id") Long menuId) {
        RoleMenuRef menuRef = roleService.getRoleMenuByRoleIdAndMenuId(roleId, menuId);

        GetRoleMenuRO menuRO = new GetRoleMenuRO();

        return null;
    }

    @PostMapping(value = "{role_id}/create_role_root_menu")
    @ResponseBody
    public ResponseBodyBean createRoleRootMenu(@PathVariable(value = "role_id") Long roleId,
                                               @RequestBody @Valid CreateRoleRootMenuPLO plo) {
        roleService.createRoleRootMenu(roleId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @PostMapping(value = "{role_id}/create_role_sub_menu")
    @ResponseBody
    public ResponseBodyBean createRoleSubMenu(@PathVariable(value = "role_id") Long roleId,
                                              @RequestBody @Valid CreateRoleSubMenuPLO plo) {
        roleService.createRoleSubMenu(roleId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @PutMapping(value = "{role_id}/modify_role_root_menu/{menu_id}")
    @ResponseBody
    public ResponseBodyBean modifyRoleRootMenu(@PathVariable(value = "role_id") Long roleId,
                                               @PathVariable(value = "menu_id") Long menuId,
                                               @RequestBody ModifyRoleRootMenuPLO plo) {
        roleService.modifyRoleRootMenu(roleId, menuId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @PutMapping(value = "{role_id}/modify_role_sub_menu/{menu_id}")
    @ResponseBody
    public ResponseBodyBean modifyRoleSubMenu(@PathVariable(value = "role_id") Long roleId,
                                              @PathVariable(value = "menu_id") Long menuId,
                                              @RequestBody ModifyRoleSubMenuPLO plo) {
        roleService.modifyRoleSubMenu(roleId, menuId, plo);
        return ResponseBodyBean.ofSuccess();
    }
}
