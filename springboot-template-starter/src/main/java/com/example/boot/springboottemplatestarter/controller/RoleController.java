package com.example.boot.springboottemplatestarter.controller;

import com.example.boot.springboottemplatedomain.role.payload.FindAllRolePLO;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatedomain.role.response.FindAllRoleRO;
import com.example.boot.springboottemplatestarter.response.ResponseBodyBean;
import com.example.boot.springboottemplatestarter.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping(value = "modify/{role_id}")
    public String modifyRole(@PathVariable(value = "role_id") Long roleId, Model model) {
        return  "system/role/modify";
    }
}
