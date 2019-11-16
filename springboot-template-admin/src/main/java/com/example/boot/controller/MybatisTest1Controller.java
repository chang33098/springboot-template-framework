package com.example.boot.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.model.ResponseBodyBean;
import com.example.boot.model.mybatistest.MybatisTablePLO;
import com.example.boot.model.mybatistest.MybatisTest1;
import com.example.boot.service.MybatisTest1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chang_
 * @since 2019-11-10
 */
@Slf4j
@Controller
@RequestMapping("mybatisTest1")
public class MybatisTest1Controller {

    private final MybatisTest1Service mybatisTest1Service;

    @Autowired
    public MybatisTest1Controller(MybatisTest1Service mybatisTest1Service) {
        this.mybatisTest1Service = mybatisTest1Service;
    }

    @GetMapping(value = "list")
    @ResponseBody
    public ResponseBodyBean<IPage<MybatisTest1>> list(MybatisTablePLO plo) {
        Page<MybatisTest1> page = new Page<>(plo.getPageNo(), plo.getPageSize());
        IPage<MybatisTest1> pageList = mybatisTest1Service.page(page);
        return ResponseBodyBean.ofSuccess(pageList);
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean create(@RequestBody MybatisTest1 params) {
        mybatisTest1Service.save(params);
        return ResponseBodyBean.ofSuccess("创建成功");
    }

    @PutMapping(value = "modify")
    @ResponseBody
    public ResponseBodyBean modify(@RequestBody MybatisTest1 params) {
        MybatisTest1 entity = mybatisTest1Service.getById(params.getId());
        Assert.notNull(entity, "无法获取ID[{}]的数据", params.getId());

        entity.setStringColumn(params.getStringColumn());
        entity.setIntColumn(params.getIntColumn());
        entity.setDoubleColumn(params.getDoubleColumn());
        entity.setTimestampColumn(new Timestamp(System.currentTimeMillis()));

        mybatisTest1Service.save(entity);
        return ResponseBodyBean.ofSuccess("编辑成功");
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseBodyBean delete(@RequestParam(value = "id") Long id) {
        boolean status = mybatisTest1Service.removeById(id);
        Assert.isTrue(status, "不存在ID[{}]的数据", id);
        return ResponseBodyBean.ofSuccess("删除成功");
    }
}
