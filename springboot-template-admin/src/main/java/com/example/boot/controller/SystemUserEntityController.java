package com.example.boot.controller;

import com.example.boot.springboottemplatebase.base.controller.BaseEntityController;
import com.example.boot.springboottemplatebase.domain.systemuser.entity.SystemUserEntity;
import com.example.boot.springboottemplatebase.service.SystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/11/17 22:14
 */
@Slf4j
@Controller
@RequestMapping(value = "system/user")
public class SystemUserEntityController extends BaseEntityController<SystemUserEntity, SystemUserService> {

    public SystemUserEntityController() {
        super("/system/user", "system_user");
    }
}
