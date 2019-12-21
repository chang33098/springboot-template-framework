package com.example.boot.controller;

import com.example.boot.springboottemplatebase.base.controller.BaseEntityController;
import com.example.boot.springboottemplatebase.domain.systemrole.entity.SystemRoleEntity;
import com.example.boot.springboottemplatebase.service.SystemRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/11/17 22:15
 */
@Slf4j
@Controller
@RequestMapping(value = "system/role")
public class SystemRoleEntityController extends BaseEntityController<SystemRoleEntity, SystemRoleService> {

    public SystemRoleEntityController() {
        super("'/system/role", "system_role");
    }
}
