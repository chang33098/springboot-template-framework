package com.example.boot.springboottemplatestarterbase.base.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatestarterbase.base.entity.BaseEntity;
import com.example.boot.springboottemplatestarterbase.base.exception.ResourceNotFoundException;
import com.example.boot.springboottemplatestarterbase.base.response.ResponseBodyBean;
import com.example.boot.springboottemplatestarterbase.common.query.QueryGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Slf4j
/**
 * description: base controller 通用的控制器类
 * <p>该类的作用主要是提供了公用的增删改查方法</p>
 *
 * @author chang_
 * @date 2020-02-16 16:20
 */
public abstract class BaseController<T extends BaseEntity, S extends IService<T>> {

    @Autowired
    private S service;

    @GetMapping
    public ResponseBodyBean<IPage<T>> list(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                                           @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
                                           T payload) throws IllegalAccessException {
        QueryWrapper<T> wrapper = QueryGenerator.generateQueryWrapper(payload, payload.getClass());
        IPage<T> page = new Page<>(pageNo, pageSize);
        service.page(page, wrapper);
        return ResponseBodyBean.ofSuccess(page);
    }

    @PostMapping
    public ResponseBodyBean create(@RequestBody @Valid T payload) {
        service.save(payload);
        return ResponseBodyBean.ofSuccess("create success!");
    }

    @PutMapping
    public ResponseBodyBean modify(@RequestBody @Valid T payload) {
        Optional<T> optionalT = Optional.ofNullable(service.getById(payload.getId()));
        T entity = optionalT.orElseThrow(() -> new ResourceNotFoundException("无法获取实体对象"));

        BeanUtil.copyProperties(payload, entity, "id", "createBy", "createTime", "deleted");
        service.saveOrUpdate(entity);
        return ResponseBodyBean.ofSuccess("modify success!");
    }

    @DeleteMapping(value = "{data-id}")
    public ResponseBodyBean delete(@PathVariable(value = "data-id") Long dataId) {
        service.removeById(dataId);
        return ResponseBodyBean.ofSuccess("delete success!");
    }
}
