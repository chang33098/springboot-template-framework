package com.example.boot.springboottemplatesecurity.security;

import com.example.boot.springboottemplatesecurity.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

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
    private SecurityService securityService;

    // TODO: 2019/8/24 统一[spring security]的日志输出格式

    /**
     * 初始化security登录用户的信息(通过username获取)
     *
     * @param credentials 登录账号
     * @return spring-security user details
     * @throws UsernameNotFoundException
     * @throws IllegalArgumentException
     */
    @Override
    public UserDetails loadUserByUsername(final String credentials) throws UsernameNotFoundException, IllegalArgumentException {
//        final String username = credentials.trim(); //去除两边的空格
//        SystemUser user = securityService.securityGetUserByUsername(username).orElseThrow(() -> {
//            log.error("User's account not found: {}", username);
//            return new UsernameNotFoundException("User's account not found: " + username);
//        });
//
//        SystemRole role = user.getRole();
//        Assert.notNull(role, "账号[{}]的系统角色为空", username);
//
//        List<RoleMenuRef> roleMenus = securityService.securityGetRoleMenuListByRoleId(role.getId()); //加载角色所对应的菜单
//
//        List<Long> menuIds = roleMenus.stream().map(RoleMenuRef::getId).collect(Collectors.toList());
//        List<RoleMenuPermissionRef> rolePermissions = securityService.securityGetRoleMenuPermissionListByMenuIds(menuIds); //加载角色菜单对应的权限
//
//        return UserPrincipal.create(user, roleMenus, rolePermissions);

        return null;
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
