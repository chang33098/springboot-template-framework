package com.example.boot.springboottemplatestarter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.role.payload.CreateRoleMenuPLO;
import com.example.boot.springboottemplatedomain.role.payload.CreateRolePLO;
import com.example.boot.springboottemplatedomain.role.payload.FindAllRolePLO;
import com.example.boot.springboottemplatedomain.role.payload.ModifyRolePLO;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatedomain.role.response.FindAllRoleRO;
import com.example.boot.springboottemplatedomain.role.response.ModifyRoleRO;
import com.example.boot.springboottemplatedomain.role.response.RoleMenuRO;
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

    @GetMapping(value = "list")
    @ResponseBody
    public ResponseBodyBean<Page<FindAllRoleRO>> findAllRole(FindAllRolePLO plo) {
        Page<SystemRole> rolePage = roleService.findAllRole(plo);

        List<FindAllRoleRO> roleROS = FindAllRoleRO.createFindAllRoleROS(rolePage.getContent());
        Page<FindAllRoleRO> roleROPage = new PageImpl<>(roleROS,
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

        return  "system/role/role_modify";
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

    @GetMapping(value = "modify_role_menu/{role_id}")
    public String roleMenu(@PathVariable(value = "role_id") Long roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "system/role/role_menu";
    }

    @GetMapping(value = "menus/{role_id}")
    @ResponseBody
    public ResponseBodyBean<List<RoleMenuRO>> getRoleMenus(@PathVariable(value = "role_id") Long roleId) {
        List<RoleMenuRef> menuRefs = roleService.securityGetAllRoleMenuByRoleId(roleId);
        List<RoleMenuRO> menuROS = menuRefs.stream().map(menuRef -> {
            RoleMenuRO menuRO = new RoleMenuRO();
            menuRO.transferTreeNode(menuRef);
            return menuRO;
        }).collect(Collectors.toList());

        return ResponseBodyBean.ofSuccess(menuROS);
    }

    @PostMapping(value = "create_role_menu")
    @ResponseBody
    public ResponseBodyBean createRoleMenu(@RequestBody @Valid CreateRoleMenuPLO plo) {

        return ResponseBodyBean.ofSuccess();
    }
}
