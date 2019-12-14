package com.example.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.springboottemplatebase.base.response.ResponseBodyBean;
import com.example.boot.springboottemplatebase.domain.systemrole.entity.SystemRoleEntity;
import com.example.boot.springboottemplatebase.domain.systemrole.payload.CreateRolePLO;
import com.example.boot.springboottemplatebase.domain.systemrole.payload.ModifyRolePLO;
import com.example.boot.springboottemplatebase.service.SystemRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/11/17 22:15
 */
@Slf4j
@Controller
@RequestMapping(value = "system/role")
public class SystemRoleController {

    private final SystemRoleService roleService;

    @Autowired
    public SystemRoleController(SystemRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "list")
    @ResponseBody
    public ResponseBodyBean<IPage<SystemRoleEntity>> list(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                                                          @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
                                                          SystemRoleEntity payload) {
        LambdaQueryWrapper<SystemRoleEntity> wrapper = new QueryWrapper<SystemRoleEntity>().lambda();
        IPage<SystemRoleEntity> page = new Page<>(pageNo, pageSize);
        roleService.page(page, wrapper);
        return ResponseBodyBean.ofData(page);
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean create(@RequestBody @Valid CreateRolePLO payload) {
        roleService.create(payload);
        return ResponseBodyBean.ofSuccess();
    }

    @PutMapping(value = "modify")
    @ResponseBody
    public ResponseBodyBean modify(@RequestBody @Valid ModifyRolePLO payload) {
        roleService.modify(payload);
        return ResponseBodyBean.ofSuccess();
    }

    @DeleteMapping(value = "delete")
    public ResponseBodyBean delete(@RequestParam(value = "role_id") Long roleId) {
        roleService.delete(roleId);
        return ResponseBodyBean.ofSuccess();
    }
}
