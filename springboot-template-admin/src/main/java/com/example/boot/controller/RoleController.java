package com.example.boot.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.model.role.payload.*;
import com.example.boot.model.role.response.*;
import com.example.boot.response.ResponseBodyBean;
import com.example.boot.service.PageService;
import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuPermissionRef;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    private final PageService pageService;

    @Autowired
    public RoleController(RoleService roleService, PageService pageService) {
        this.roleService = roleService;
        this.pageService = pageService;
    }

    @GetMapping
    public String role() {
        return "system/role/role_list";
    }

    @GetMapping(value = "table")
    @ResponseBody
    public ResponseBodyBean<Page<FindRoleTableRO>> findRoleTable(FindRoleTablePLO plo) {
        Page<SystemRole> rolePage = roleService.findRoleTable(plo);

        List<FindRoleTableRO> roleROS = new ArrayList<>(rolePage.getContent().size());
        rolePage.getContent().forEach(role -> {
            FindRoleTableRO roleRO = new FindRoleTableRO();
            BeanUtil.copyProperties(role, roleRO);

            roleROS.add(roleRO);
        });
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

    @GetMapping(value = "{role_id}/get_role_menu")
    @ResponseBody
    public ResponseBodyBean<List<GetRoleMenuTreeRO>> getRoleMenuTree(@PathVariable(value = "role_id") Long roleId) {
        List<RoleMenuRef> menuRefs = roleService.getRoleRootMenuListByRoleId(roleId);
        List<GetRoleMenuTreeRO> menuROS = menuRefs.stream().map(menuRef -> {
            GetRoleMenuTreeRO menuRO = new GetRoleMenuTreeRO();
            menuRO.transferTree(menuRef);
            return menuRO;
        }).collect(Collectors.toList());

        return ResponseBodyBean.ofSuccess(menuROS);
    }

//    @GetMapping(value = "{role_id}/modify_role_root_menu/{menu_id}")
//    @ResponseBody
//    public ResponseBodyBean<ModifyRoleRootMenuRO> modifyRoleRootMenu(@PathVariable(value = "role_id") Long roleId,
//                                                                     @PathVariable(value = "menu_id") Long menuId) {
//        RoleMenuRef menuRef = roleService.getRoleMenuByRoleIdAndMenuId(roleId, menuId);
//
//        ModifyRoleRootMenuRO menuRO = new ModifyRoleRootMenuRO();
//        BeanUtil.copyProperties(menuRef, menuRO);
//
//        menuRO.setPageId(menuRef.getPage().getId());
//
//        return ResponseBodyBean.ofSuccess(menuRO);
//    }
//
//    @RequestMapping(value = "{role_id}/modify_role_sub_menu/{menu_id}")
//    @ResponseBody
//    public ResponseBodyBean<ModifyRoleSubMenuRO> modifyRoleSubMenu(@PathVariable(value = "role_id") Long roleId,
//                                                                   @PathVariable(value = "menu_id") Long menuId) {
//        RoleMenuRef menuRef = roleService.getRoleMenuByRoleIdAndMenuId(roleId, menuId);
//        final Long pageId = menuRef.getPage().getId();
//
//        List<PagePermissionRef> pagePermissions = pageService.getPagePermissionListById(pageId);
//        List<RoleMenuPermissionRef> menuPermissions = roleService.getRoleMenuPermissionListByMenuId(menuId);
//
//        ModifyRoleSubMenuRO menuRO = new ModifyRoleSubMenuRO();
//        BeanUtil.copyProperties(menuRef, menuRO);
//        menuRO.setParentName("父页面名称");
//        menuRO.setPageId(menuRef.getPage().getId());
//        menuRO.setPageName(menuRef.getPage().getName());
//        menuRO.setPageCode(menuRef.getPage().getCode());
//        menuRO.setPageUrl(menuRef.getPage().getUrl());
//
//        List<Long> menuPermissionId = menuPermissions.stream().map(menuPermission -> menuPermission.getPermission().getId()).collect(Collectors.toList());
//        List<ModifyRoleSubMenuRO.PagePermission> pagePermissionsROS = pagePermissions.stream().map(pagePermission -> {
//            ModifyRoleSubMenuRO.PagePermission pagePermissionRO = new ModifyRoleSubMenuRO.PagePermission();
//            pagePermissionRO.setPermissionId(pagePermission.getId());
//            pagePermissionRO.setPermissionName(pagePermission.getPermission().getName());
//            pagePermissionRO.setChecked(menuPermissionId.contains(pagePermission.getId()));
//            return pagePermissionRO;
//        }).collect(Collectors.toList());
//
//        menuRO.setPagePermissions(pagePermissionsROS);
//
//        return ResponseBodyBean.ofSuccess(menuRO);
//    }

    @GetMapping(value = "{role_id}/create_role_root_menu")
    public String createRoleRootMenu(@PathVariable(value = "role_id") Long roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "system/role/rolemenu/create_parent_menu";
    }

    @PostMapping(value = "{role_id}/create_role_root_menu")
    @ResponseBody
    public ResponseBodyBean createRoleRootMenu(@PathVariable(value = "role_id") Long roleId,
                                               @RequestBody @Valid CreateRoleRootMenuPLO plo) {
        roleService.createRoleRootMenu(roleId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "{role_id}/role_menu/{parent_id}/create_role_sub_menu")
    public String createRoleSubMenu(@PathVariable(value = "role_id") Long roleId,
                                    @PathVariable(value = "parent_id") Long parentId, Model model) {
        RoleMenuRef parentMenu = roleService.getRoleMenuByRoleIdAndMenuId(roleId, parentId);

        model.addAttribute("roleId", roleId);
        model.addAttribute("parentId", parentId);
        model.addAttribute("parentMenuName", parentMenu.getMenuName());

        return "system/role/rolemenu/create_sub_menu";
    }

    @PostMapping(value = "{role_id}/role_menu/{parent_id}/create_role_sub_menu")
    @ResponseBody
    public ResponseBodyBean createRoleSubMenu(@PathVariable(value = "role_id") Long roleId,
                                              @PathVariable(value = "parent_id") Long parentId,
                                              @RequestBody @Valid CreateRoleSubMenuPLO plo) {
        roleService.createRoleSubMenu(roleId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "{role_id}/modify_role_root_menu/{menu_id}")
    public String modifyRoleRootMenu(@PathVariable(value = "role_id") Long roleId,
                                     @PathVariable(value = "menu_id") Long menuId, Model model) {
        RoleMenuRef menuRef = roleService.getRoleMenuByRoleIdAndMenuId(roleId, menuId);

        ModifyRoleRootMenuRO menuRO = new ModifyRoleRootMenuRO();
        BeanUtil.copyProperties(menuRef, menuRO);
        menuRO.setPageId(menuRef.getPage().getId());

        model.addAttribute("menu", menuRO);

        return "system/role/rolemenu/modify_role_root_menu";
    }

    @PutMapping(value = "{role_id}/modify_role_root_menu/{menu_id}")
    @ResponseBody
    public ResponseBodyBean modifyRoleRootMenu(@PathVariable(value = "role_id") Long roleId,
                                               @PathVariable(value = "menu_id") Long menuId,
                                               @RequestBody ModifyRoleRootMenuPLO plo) {
        roleService.modifyRoleRootMenu(roleId, menuId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "{role_id}/role_menu/{parent_id}/modify_role_sub_menu/{menu_id}")
    public String modifyRoleSubMenu(@PathVariable(value = "role_id") Long roleId,
                                    @PathVariable(value = "parent_id") Long parentId,
                                    @PathVariable(value = "menu_id") Long menuId, Model model) {
        model.addAttribute("roleId", roleId);
        model.addAttribute("parentId", parentId);
        model.addAttribute("menuId", menuId);

        RoleMenuRef parentMenu = roleService.getRoleMenuByRoleIdAndMenuId(roleId, parentId);
        model.addAttribute("parentMenuName", parentMenu.getMenuName());

        RoleMenuRef menuRef = roleService.getRoleMenuByRoleIdAndMenuId(roleId, menuId); //获取菜单信息
        ModifyRoleSubMenuRO menuRO = new ModifyRoleSubMenuRO();
        BeanUtil.copyProperties(menuRef, menuRO);

        model.addAttribute("menu", menuRO);

        return "modify_role_sub_menu";
    }

    @PutMapping(value = "{role_id}/role_menu/{parent_id}/modify_role_sub_menu/{menu_id}")
    @ResponseBody
    public ResponseBodyBean modifyRoleSubMenu(@PathVariable(value = "role_id") Long roleId,
                                              @PathVariable(value = "parent_id") Long parentId,
                                              @PathVariable(value = "menu_id") Long menuId,
                                              @RequestBody @Valid ModifyRoleSubMenuPLO plo) {
        roleService.modifyRoleSubMenu(roleId, menuId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "choose_menu_icon")
    public String chooseIcon() {
        return "system/role/choose_menu_icon";
    }
}
