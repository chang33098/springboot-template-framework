package com.example.boot.springboottemplatestarter.controller;

import com.example.boot.springboottemplatedomain.user.payload.FindUserTablePLO;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import com.example.boot.springboottemplatedomain.user.response.FindUserTableRO;
import com.example.boot.springboottemplatestarter.response.ResponseBodyBean;
import com.example.boot.springboottemplatestarter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ANdady on 2019/7/18.
 */
@Slf4j
@Controller
@RequestMapping(value = "/system/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String user() {
        return "system/user/user_list";
    }

    @GetMapping(value = "table")
    @ResponseBody
    public ResponseBodyBean<Page<FindUserTableRO>> findUserTable(FindUserTablePLO plo) {
        Page<SystemUser> userPage = userService.findUserTable(plo);

        List<FindUserTableRO> userROS = FindUserTableRO.create(userPage.getContent());
        Page<FindUserTableRO> userROPage = new PageImpl<>(userROS, userPage.getPageable(), userPage.getTotalElements());

        return ResponseBodyBean.ofSuccess(userROPage);
    }
}
