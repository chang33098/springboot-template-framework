package com.example.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.response.ResponseBodyBean;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.CreateDictPLO;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.ModifyDictPLO;
import com.example.boot.springboottemplatebase.service.SystemDictService;
import com.example.boot.springboottemplatebase.domain.systemdict.persistent.SystemDict;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chang_
 * @since 2019-11-13
 */
@Slf4j
@Controller
@RequestMapping(value = "system/dict")
public class SystemDictController {

    private final SystemDictService dictService;

    @Autowired
    public SystemDictController(SystemDictService dictService) {
        this.dictService = dictService;
    }

    @GetMapping(value = "table")
    @ResponseBody
    public ResponseBodyBean<IPage<SystemDict>> table(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                                                     @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
                                                     SystemDict payload) {
        LambdaQueryWrapper<SystemDict> wrapper = new QueryWrapper<SystemDict>().lambda();
        IPage<SystemDict> page = new Page<>(pageNo, pageSize);
        dictService.page(page, wrapper);
        return ResponseBodyBean.ofSuccess(null);
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean create(@RequestBody @Valid CreateDictPLO payload) {
        dictService.create(payload);
        return ResponseBodyBean.ofSuccess("创建数据字典成功");
    }

    @PutMapping(value = "modify")
    @ResponseBody
    public ResponseBodyBean modify(@RequestBody @Valid ModifyDictPLO payload) {
        dictService.modify(payload);
        return ResponseBodyBean.ofSuccess("修改数据字典成功");
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseBodyBean delete(@RequestParam(value = "dict_id") Long dictId) {
        dictService.delete(dictId);
        return ResponseBodyBean.ofSuccess("删除数据字典成功");
    }
}
