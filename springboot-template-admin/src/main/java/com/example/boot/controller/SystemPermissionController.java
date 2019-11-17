package com.example.boot.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.model.ResponseBodyBean;
import com.example.boot.model.permission.payload.CreatePermissionPLO;
import com.example.boot.model.permission.payload.GetPermissionTablePLO;
import com.example.boot.model.permission.payload.ModifyPermissionPLO;
import com.example.boot.service.SystemPermissionService;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * @author chang_
 * @since 2019-11-16
 */
@Slf4j
@Controller
@RequestMapping("system/permission")
public class SystemPermissionController {

    private final SystemPermissionService permissionService;

    @Autowired
    public SystemPermissionController(SystemPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping(value = "table")
    @ResponseBody
    public ResponseBodyBean<IPage<SystemPermission>> table(GetPermissionTablePLO plo) {
        Wrapper<SystemPermission> wrapper = new QueryWrapper<SystemPermission>().lambda()
                .like(false, SystemPermission::getName, plo.getName())
                .eq(false, SystemPermission::getCode, plo.getCode());

        Page<SystemPermission> page = new Page<>(plo.getPageNo(), plo.getPageSize());
        IPage<SystemPermission> table = permissionService.page(page, wrapper);

        return ResponseBodyBean.ofSuccess(table);
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean create(@RequestBody @Valid CreatePermissionPLO permissionPLO) {
        permissionService.create(permissionPLO);
        return ResponseBodyBean.ofSuccess("创建成功");
    }

    @PutMapping(value = "modify")
    public ResponseBodyBean modify(@RequestBody @Valid ModifyPermissionPLO permissionPLO) {
        permissionService.modify(permissionPLO);
        return ResponseBodyBean.ofSuccess("编辑成功");
    }

    @DeleteMapping(value = "delete")
    public ResponseBodyBean delete(@RequestParam(value = "permission_id") Long permissionId) {
        permissionService.delete(permissionId);
        return ResponseBodyBean.ofSuccess("删除成功");
    }
}
