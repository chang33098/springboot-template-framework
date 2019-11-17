package com.example.boot.controller;

import com.example.boot.service.SystemRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
