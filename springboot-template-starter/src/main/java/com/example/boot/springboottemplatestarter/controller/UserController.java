package com.example.boot.springboottemplatestarter.controller;

import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatedomain.role.response.GetRoleListRO;
import com.example.boot.springboottemplatedomain.user.payload.CreateUserPLO;
import com.example.boot.springboottemplatedomain.user.payload.FindUserTablePLO;
import com.example.boot.springboottemplatedomain.user.payload.ModifyUserPLO;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import com.example.boot.springboottemplatedomain.user.response.FindUserTableRO;
import com.example.boot.springboottemplatestarter.response.ResponseBodyBean;
import com.example.boot.springboottemplatestarter.service.RoleService;
import com.example.boot.springboottemplatestarter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ANdady on 2019/7/18.
 */
@Slf4j
@Controller
@RequestMapping(value = "/system/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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

    @GetMapping(value = "get/{user_id}")
    public String getUser(@PathVariable(value = "user_id") Long userId) {

        return "system/user/user_detail";
    }

    @GetMapping(value = "create")
    public String createUser(Model model) {
        List<GetRoleListRO> roles = roleService.getRoleList();
        model.addAttribute("roles", roles);

        return "system/user/user_create";
    }

    @RequestMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean createUser(@RequestBody @Valid CreateUserPLO plo) {

        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "modify/{user_id}")
    public String modifyUser(@PathVariable(value = "user_id") Long userId, Model model) {
        return "system/user/user_modify";
    }

    @PutMapping(value = "modify/{user_id}")
    @ResponseBody
    public ResponseBodyBean modifyUser(@PathVariable(value = "user_id") Long userId,
                                       @RequestBody @Valid ModifyUserPLO plo) {
        return ResponseBodyBean.ofSuccess();
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseBodyBean delete(@PathVariable(value = "user_id") Long userId) {

        return ResponseBodyBean.ofSuccess();
    }
}
