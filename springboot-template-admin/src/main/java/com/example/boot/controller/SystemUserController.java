package com.example.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot.response.ResponseBodyBean;
import com.example.boot.springboottemplatebase.domain.systemuser.persistent.SystemUser;
import com.example.boot.springboottemplatebase.service.SystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/11/17 22:14
 */
@Slf4j
@Controller
@RequestMapping(value = "system/user")
public class SystemUserController {

    private final SystemUserService userService;

    @Autowired
    public SystemUserController(SystemUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "list")
    @ResponseBody
    public ResponseBodyBean<IPage<SystemUser>> list(@RequestParam(value = "page_no") Integer pageNo,
                                                    @RequestParam(value = "page_size") Integer pageSize,
                                                    SystemUser payload) {
        LambdaQueryWrapper<SystemUser> wrapper = new QueryWrapper<SystemUser>().lambda();
        IPage<SystemUser> page = new Page<>(pageNo, pageSize);
        userService.page(page, wrapper);
        return ResponseBodyBean.ofData(page);
    }
}
