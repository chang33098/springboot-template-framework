package com.example.boot.springboottemplatebase.base.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.base.response.ResponseBodyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * <p>BaseController的构造方法</p>
     *
     * @param basePath  视图的基础路径
     * @param modelName 模块名称(英文)
     */
    public BaseController(String basePath, String modelName) {
        this.basePath = basePath;
        this.modelName = modelName;
        this.listViewName = modelName + StrUtil.C_UNDERLINE + "list";
        this.createViewName = modelName + StrUtil.C_UNDERLINE + "create";
        this.modifyViewName = modelName + StrUtil.C_UNDERLINE + "modify";
    }

    /**
     * <p>BaseController构造方法</p>
     * 自定义[列表视图]、[创建视图]、[编辑视图]的名称
     *
     * @param basePath       视图的基础路径
     * @param modelName      模块名称(英文)
     * @param listViewName   列表视图名称
     * @param createViewName 创建视图名称
     * @param modifyViewName 编辑视图名称
     */
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
    public String modifyView(@RequestParam(value = "data-id") String dataId) {
        return basePath + StrUtil.SLASH + modifyViewName;
    }

    @GetMapping(value = "list")
    @ResponseBody
    public ResponseBodyBean<IPage<T>> list(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                                           @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
                                           T payload) {
        IPage<T> page = new Page<>(pageNo, pageSize);
        service.page(page);
        return ResponseBodyBean.ofSuccess(page);
    }
}
