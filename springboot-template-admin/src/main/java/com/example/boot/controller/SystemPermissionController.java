package com.example.boot.controller;

import com.example.boot.springboottemplatebase.base.controller.BaseController;
import com.example.boot.springboottemplatebase.domain.systempermission.entity.SystemPermissionEntity;
import com.example.boot.springboottemplatebase.service.SystemPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author chang_
 * @since 2019-11-16
 */
@Slf4j
@Controller
@RequestMapping("system/permission")
public class SystemPermissionController extends BaseController<SystemPermissionEntity, SystemPermissionService> {

    public SystemPermissionController() {
        super("/system/permission", "system_permission");
    }
}
