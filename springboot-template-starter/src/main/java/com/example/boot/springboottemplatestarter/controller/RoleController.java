package com.example.boot.springboottemplatestarter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.role.payload.CreateRolePLO;
import com.example.boot.springboottemplatedomain.role.payload.FindAllRolePLO;
import com.example.boot.springboottemplatedomain.role.payload.ModifyRolePLO;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatedomain.role.response.FindAllRoleRO;
import com.example.boot.springboottemplatedomain.role.response.ModifyRoleRO;
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
    public String createRole(Model model) {
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

    @GetMapping(value = "/modify/{role_id}/menu")
    public String modifyRoleMenu(@PathVariable(value = "role_id") Long roleId) {
        return "system/role/role_menu";
    }
}
