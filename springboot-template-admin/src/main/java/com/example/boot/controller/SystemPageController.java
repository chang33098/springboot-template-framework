package com.example.boot.controller;

import com.example.boot.springboottemplatebase.base.controller.BaseController;
import com.example.boot.springboottemplatebase.domain.systempage.entity.SystemPageEntity;
import com.example.boot.springboottemplatebase.service.SystemPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>系统模块-系统页面</p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Slf4j
@Controller
@RequestMapping("system/page")
public class SystemPageController extends BaseController<SystemPageEntity, SystemPageService> {

    public SystemPageController() {
        super("/system/page", "system_page");
    }

}
