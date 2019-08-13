package com.example.boot.springboottemplatestarter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ANdady on 2019/7/18.
 */
@Slf4j
@Controller
@RequestMapping(value = "/system/user")
public class UserController {

    @GetMapping
    public String user() {
        return "system/user/user_list";
    }
}
