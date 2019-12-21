package com.example.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.boot.springboottemplatebase.base.controller.BaseEntityController;
import com.example.boot.springboottemplatebase.base.response.ResponseBodyBean;
import com.example.boot.springboottemplatebase.domain.systempage.entity.SystemPageEntity;
import com.example.boot.springboottemplatebase.service.SystemPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>系统模块-系统页面</p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Slf4j
@Controller
@RequestMapping("system/page")
public class SystemPageEntityController extends BaseEntityController<SystemPageEntity, SystemPageService> {

    public SystemPageEntityController() {
        super("/system/page", "system_page");
    }

}
