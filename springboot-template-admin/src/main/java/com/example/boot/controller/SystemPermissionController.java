package com.example.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.response.ResponseBodyBean;
import com.example.boot.springboottemplatebase.domain.systempermission.payload.CreatePermissionPLO;
import com.example.boot.springboottemplatebase.domain.systempermission.payload.ModifyPermissionPLO;
import com.example.boot.springboottemplatebase.domain.systempermission.entity.SystemPermissionEntity;
import com.example.boot.springboottemplatebase.service.SystemPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "list")
    @ResponseBody
    public ResponseBodyBean<IPage<SystemPermissionEntity>> list(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                                                                @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
                                                                SystemPermissionEntity payload) {
        LambdaQueryWrapper<SystemPermissionEntity> wrapper = new QueryWrapper<SystemPermissionEntity>().lambda();
        Page<SystemPermissionEntity> page = new Page<>(pageNo, pageSize);
        IPage<SystemPermissionEntity> table = permissionService.page(page, wrapper);
        return ResponseBodyBean.ofSuccess(table);
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean create(@RequestBody @Valid CreatePermissionPLO payload) {
        permissionService.create(payload);
        return ResponseBodyBean.ofSuccess("创建成功");
    }

    @PutMapping(value = "modify")
    public ResponseBodyBean modify(@RequestBody @Valid ModifyPermissionPLO payload) {
        permissionService.modify(payload);
        return ResponseBodyBean.ofSuccess("编辑成功");
    }

    @DeleteMapping(value = "delete")
    public ResponseBodyBean delete(@RequestParam(value = "permission_id") Long permissionId) {
        permissionService.delete(permissionId);
        return ResponseBodyBean.ofSuccess("删除成功");
    }
}
