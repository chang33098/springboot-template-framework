package com.example.boot.springboottemplatebase.base.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chang_
 * @description 公共controller
 */
@Slf4j
public abstract class BaseController<T, S extends IService<T>> {

    private final String basePath;
    private final String modelName;
    private final String listViewName;
    private final String createViewName;
    private final String modifyViewName;

    @Autowired
    private S service;

    public BaseController(String basePath, String modelName) {
        this.basePath = basePath;
        this.modelName = modelName;
        this.listViewName = modelName + StrUtil.C_UNDERLINE + "list";
        this.createViewName = modelName + StrUtil.C_UNDERLINE + "create";
        this.modifyViewName = modelName + StrUtil.C_UNDERLINE + "modify";
    }

    public BaseController(String basePath, String modelName, String listViewName, String createViewName, String modifyViewName) {
        this.basePath = basePath;
        this.modelName = modelName;
        this.listViewName = this.modelName + StrUtil.C_UNDERLINE + listViewName;
        this.createViewName = this.modelName + StrUtil.C_UNDERLINE + createViewName;
        this.modifyViewName = this.modelName + StrUtil.C_UNDERLINE + modifyViewName;
    }

    @GetMapping(value = "list-view")
    public String listView() {
        return basePath + StrUtil.SLASH + listViewName;
    }

    @GetMapping(value = "create-view")
    public String createView() {
        return basePath + StrUtil.SLASH + createViewName;
    }

    @GetMapping(value = "modify-view")
    public String modify(@RequestParam(value = "data-id") String dataId) {
        return basePath + StrUtil.SLASH + modifyViewName;
    }
}
