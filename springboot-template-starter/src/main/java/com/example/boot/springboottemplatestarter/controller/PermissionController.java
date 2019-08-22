package com.example.boot.springboottemplatestarter.controller;

import com.example.boot.springboottemplatedomain.permission.payload.CreatePermissionPLO;
import com.example.boot.springboottemplatedomain.permission.payload.FindPermissionTablePLO;
import com.example.boot.springboottemplatedomain.permission.payload.ModifyPermissionPLO;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import com.example.boot.springboottemplatedomain.permission.response.FindPermissionTableRO;
import com.example.boot.springboottemplatedomain.permission.response.ModifyPermissionRO;
import com.example.boot.springboottemplatestarter.response.ResponseBodyBean;
import com.example.boot.springboottemplatestarter.service.PermissionService;
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
 * @date 2019/8/11 13:10
 */
@Slf4j
@Controller
@RequestMapping(value = "/system/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public String permission() {
        return "system/permission/permission_list";
    }

    @GetMapping(value = "table")
    @ResponseBody
    public ResponseBodyBean<Page<FindPermissionTableRO>> findPermissionTable(FindPermissionTablePLO plo) {
        Page<SystemPermission> permissionPage = permissionService.findPermissionTable(plo);

        List<FindPermissionTableRO> permissionROS = FindPermissionTableRO.createFindAllPermissionROS(permissionPage.getContent());
        Page<FindPermissionTableRO> permissionROPage = new PageImpl<>(permissionROS,
                permissionPage.getPageable(), permissionPage.getTotalElements());

        return ResponseBodyBean.ofSuccess(permissionROPage);
    }

    @GetMapping(value = "create")
    public String createPermission() {
        return "system/permission/permission_create";
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean createPermission(@RequestBody @Valid CreatePermissionPLO plo) {
        permissionService.createPermission(plo);
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "modify/{permission_id}")
    public String modifyPermission(@PathVariable(value = "permission_id") Long permissionId, Model model) {
        SystemPermission permission = permissionService.getPermissionById(permissionId);

        ModifyPermissionRO permissionRO = ModifyPermissionRO.createModifyPermissionRO(permission);
        model.addAttribute("permission", permissionRO);

        return "system/permission/permission_modify";
    }

    @PutMapping(value = "modify/{permission_id}")
    @ResponseBody
    public ResponseBodyBean modifyPermission(@PathVariable(value = "permission_id") Long permissionId,
                                             @RequestBody @Valid ModifyPermissionPLO plo) {
        permissionService.modifyPermission(permissionId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @DeleteMapping(value = "delete/{permission_id}")
    @ResponseBody
    public ResponseBodyBean deletePermission(@PathVariable(value = "permission_id") Long permissionId) {
        permissionService.deletePermission(permissionId);
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "choose_permission")
    public String choosePermission() {
        return "system/permission/choose_permission";
    }
}
