package com.example.boot.springboottemplatebase.base.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.base.entity.BaseEntity;
import com.example.boot.springboottemplatebase.base.exception.ResourceNotFoundException;
import com.example.boot.springboottemplatebase.query.QueryGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author chang_
 * @description 公共controller
 */
@Slf4j
public abstract class BaseEntityController<T extends BaseEntity, S extends IService<T>> {

    private final String basePath;
    private final String modelName;
    private final String listViewName;
    private final String createViewName;
    private final String modifyViewName;

    @Autowired
    protected S service;

    /**
     * <p>BaseController的构造方法</p>
     *
     * @param basePath  视图的基础路径
     * @param modelName 模块名称(英文)
     */
    public BaseEntityController(String basePath, String modelName) {
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
    public BaseEntityController(String basePath, String modelName, String listViewName, String createViewName, String modifyViewName) {
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
    public String modifyView(@RequestParam(value = "data-id") String dataId, Model model) {
        T entity = service.getById(dataId);
        model.addAttribute("entity", entity);
        return basePath + StrUtil.SLASH + modifyViewName;
    }

    /**
     * 返回实体的分页数据
     *
     * @param pageNo   页码
     * @param pageSize 页数
     * @param payload  参数
     * @return 分页数据
     */
    protected IPage<T> list(Integer pageNo, Integer pageSize, T payload) throws IllegalAccessException {
        QueryWrapper<T> wrapper = QueryGenerator.generateQueryWrapper(payload, payload.getClass());

        IPage<T> page = new Page<>(pageNo, pageSize);
        service.page(page, wrapper);

        return page;
    }

    /**
     * 创建数据操作
     *
     * @param payload 请求参数
     */
    protected void create(T payload) {
        service.save(payload);
    }

    /**
     * 更新数据操作
     * <p>
     * tips: 若无法匹配更新对象，则抛出ResourceNotFoundException异常
     *
     * @param payload 请求参数
     */
    protected void modify(T payload) {
        Optional<T> optionalT = Optional.ofNullable(service.getById(payload.getId()));
        T entity = optionalT.orElseThrow(() -> new ResourceNotFoundException(StrUtil.format("无法获取实体对象 dataId:{}", payload.getId())));

        BeanUtil.copyProperties(payload, entity, "id", "createBy", "createTime", "deleted");
        service.saveOrUpdate(entity);
    }

    /**
     * 删除数据操作
     * <p>
     * tips: 若无法匹配更新对象，则抛出ResourceNotFoundException异常
     *
     * @param dataId
     */
    public void delete(Long dataId) {
        int exist = service.count(new QueryWrapper<T>().lambda().eq(T::getId, dataId));
        Assert.isTrue(exist > 0, StrUtil.format("无法获取实体对象 dataId:{}", dataId));

        service.removeById(dataId);
    }
}
