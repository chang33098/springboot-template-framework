package com.example.boot.controller;

import cn.hutool.core.util.StrUtil;
import com.example.boot.springboottemplatestarterbase.base.response.ResponseBodyBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "sample")
public class Sample1Controller {

    @GetMapping(value = "hello/{nickname}")
    public ResponseBodyBean<String> sayHelloToSomeone(@PathVariable(value = "nickname") String nickname) {
        return ResponseBodyBean.ofSuccess(StrUtil.format("hello，{}", nickname), "request success！");
    }
}
