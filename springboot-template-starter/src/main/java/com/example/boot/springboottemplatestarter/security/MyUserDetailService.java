package com.example.boot.springboottemplatestarter.security;

import cn.hutool.core.lang.Assert;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import com.example.boot.springboottemplatedomain.user.security.UserPrincipal;
import com.example.boot.springboottemplatestarter.service.RoleService;
import com.example.boot.springboottemplatestarter.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 根据登陆账号加载系统用户的信息
 * effect: 在用户登录时, 通过账号加载登陆人的信息, 以及获取其对应的后台菜单 & 资源(即权限)信息
 * author: Chang
 * createtime: 2018/10/27
 */
@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 初始化security登录用户的信息(通过username获取)
     *
     * @param username 登录账号
     * @return spring-security user details
     * @throws UsernameNotFoundException
     * @throws IllegalArgumentException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, IllegalArgumentException {
        username = username.trim(); //去除两边的空格

        SystemUser user = userService.findByUsername(username);
        if (user == null) {
            log.error("[MyUserDetailService] cannot get user info, username: {}", username);
            throw new UsernameNotFoundException("username [{" + username + "}] not exists.");
        }

        SystemRole role = user.getRole();
        Assert.notNull(role, "账号[{}]的系统角色为空", username);

        List<RoleMenuRef> roleMenus = roleService.findAllByRoleIdOrderBySortNoAsc(role.getId());
        List<SystemPermission> permissions = roleMenus.stream()
                .map(RoleMenuRef::getPermissions)
                .flatMap(Collection::stream).collect(Collectors.toList()); //获取角色对应的权限信息

        return UserPrincipal.create(user, roleMenus, permissions);
    }

    /**
     * 若登陆的账号为超级管理员, 则执行以下流程
     * <p>
     * 注: 系统的超级管理员默认加载所有的菜单 & 资源(权限)信息
     * <p>
     * 超级管理员的登录账号和密码配置在application.yml文件中
     *
     * @param sysuser 超级管理员
     */
//    private void superAdmin(SystemUser sysuser) {
//        List<SystemMenu> sysmenus = sysMenuService.findAllParentNode();
//        List<SystemResource> resources = resService.findAllSysRes();
//
//        SystemRole adminrole = new SystemRole(new HashSet<>(sysmenus), new HashSet<>(resources));
//        sysuser.setRole(adminrole);
//    }
}