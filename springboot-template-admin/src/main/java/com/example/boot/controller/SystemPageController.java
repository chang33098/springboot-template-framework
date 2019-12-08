package com.example.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.response.ResponseBodyBean;
import com.example.boot.springboottemplatebase.domain.systempage.payload.CreatePagePLO;
import com.example.boot.springboottemplatebase.domain.systempage.payload.ModifyPagePLO;
import com.example.boot.springboottemplatebase.service.SystemPageService;
import com.example.boot.springboottemplatebase.domain.systempage.entity.SystemPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

/**
 * 系统模块-系统页面
 *
 * @author chang_
 * @since 2019-11-16
 */
@Slf4j
@Controller
@RequestMapping("system/page")
public class SystemPageController {

    private final SystemPageService pageService;

    @Autowired
    public SystemPageController(SystemPageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping(value = "list")
    @ResponseBody
    public ResponseBodyBean<IPage<SystemPage>> list(@RequestParam(value = "page_no") Integer pageNo,
                                                     @RequestParam(value = "page_size") Integer pageSize,
                                                     SystemPage payload) {
        LambdaQueryWrapper<SystemPage> wrapper = new QueryWrapper<SystemPage>().lambda();
        Page<SystemPage> page = new Page<>(pageNo, pageSize);
        IPage<SystemPage> table = pageService.page(page, wrapper);
        return ResponseBodyBean.ofSuccess(table);
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean create(@RequestBody @Valid CreatePagePLO payload) {
        pageService.create(payload);
        return ResponseBodyBean.ofSuccess("创建成功");
    }

    @PutMapping(value = "modify")
    @ResponseBody
    public ResponseBodyBean modify(@RequestBody @Valid ModifyPagePLO payload) {
        pageService.modify(payload);
        return ResponseBodyBean.ofSuccess("编辑成功");
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseBodyBean delete(@RequestParam(value = "page_id") Long pageId) {
        pageService.delete(pageId);
        return ResponseBodyBean.ofSuccess("删除成功");
    }
}
