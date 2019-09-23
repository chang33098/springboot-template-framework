package com.example.boot.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.model.role.response.GetRoleListRO;
import com.example.boot.model.user.payload.CreateUserPLO;
import com.example.boot.model.user.payload.FindUserTablePLO;
import com.example.boot.model.user.payload.ModifyUserPLO;
import com.example.boot.model.user.response.FindUserTableRO;
import com.example.boot.model.user.response.GetUserRO;
import com.example.boot.model.user.response.ModifyUserRO;
import com.example.boot.response.ResponseBodyBean;
import com.example.boot.service.RoleService;
import com.example.boot.service.UserService;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

        List<FindUserTableRO> userROS = new ArrayList<>(userPage.getContent().size());
        userPage.getContent().forEach(user -> {
            FindUserTableRO userRO = new FindUserTableRO();
            BeanUtil.copyProperties(user, userRO);
            userROS.add(userRO);
        });
        Page<FindUserTableRO> userROPage = new PageImpl<>(userROS, userPage.getPageable(), userPage.getTotalElements());

        return ResponseBodyBean.ofSuccess(userROPage);
    }

    @GetMapping(value = "get/{user_id}")
    public String getUser(@PathVariable(value = "user_id") Long userId, Model model) {
        SystemUser user = userService.getUserById(userId);

        GetUserRO userRO = new GetUserRO();
        BeanUtil.copyProperties(user, userRO);
        userRO.setRoleName(user.getRole().getName());

        model.addAttribute("user", userRO);

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
        userService.createUser(plo);
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "modify/{user_id}")
    public String modifyUser(@PathVariable(value = "user_id") Long userId, Model model) {
        SystemUser user = userService.getUserById(userId);
        List<GetRoleListRO> roles = roleService.getRoleList();

        ModifyUserRO userRO = new ModifyUserRO();
        BeanUtil.copyProperties(user, userRO);
        userRO.setRoleId(user.getRole().getId());

        List<ModifyUserRO.UserRole> userRoles = new ArrayList<>();
        roles.forEach(role -> {
            ModifyUserRO.UserRole userRole = new ModifyUserRO.UserRole();
            userRole.setRoleId(role.getId());
            userRole.setRoleName(role.getName());
            userRole.setChecked(userRO.getRoleId().equals(role.getId()));
            userRoles.add(userRole);
        });
        userRO.setUserRoles(userRoles);

        model.addAttribute("user", userRO);

        return "system/user/user_modify";
    }

    @PutMapping(value = "modify/{user_id}")
    @ResponseBody
    public ResponseBodyBean modifyUser(@PathVariable(value = "user_id") Long userId,
                                       @RequestBody @Valid ModifyUserPLO plo) {
        userService.modifyUser(userId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @DeleteMapping(value = "delete/{user_id}")
    public ResponseBodyBean delete(@PathVariable(value = "user_id") Long userId) {
        userService.deleteUser(userId);
        return ResponseBodyBean.ofSuccess();
    }
}
