package com.example.boot.springboottemplatesecurity.model;

import cn.hutool.core.util.StrUtil;
import com.example.boot.springboottemplatebase.domain.systemrole.query.SecurityGetRoleMenuListByRoleIdRO;
import com.example.boot.springboottemplatebase.domain.systemrole.query.SecurityGetRoleMenuPermissionListByMenuIdsQO;
import com.example.boot.springboottemplatebase.domain.systemuser.query.SecurityGetUserByUsernameQO;
import com.example.boot.springboottemplatebase.enumerate.MenuLevel;
import com.example.boot.springboottemplatebase.enumerate.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>UserPrincipal</h1>
 * <p>Custom systemuser details.</p>
 *
 * @author Chang
 * @date 2019-03-23 20:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = -53353171692896501L;

    private Long userId;
    private String username;
    private String password;
    private String phone;
    private String nickname;
    private String avatar;
    private String description;
    private String status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp lastLoginTime;

    private String roleName;
    private List<Menu> menus = new ArrayList<>();
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Create systemuser principal
     *
     * @param systemuser            systemuser po
     * @param roleMenus       systemuser platform menus
     * @param rolePermissions systemrole systempermission po list
     * @return systemuser principal
     */
    public static UserPrincipal create(SecurityGetUserByUsernameQO systemuser, List<SecurityGetRoleMenuListByRoleIdRO> roleMenus, List<SecurityGetRoleMenuPermissionListByMenuIdsQO> rolePermissions) {
        List<Menu> menus = roleMenus.stream().filter(userMenu -> Objects.equals(MenuLevel.PARENT_MENU.getType(), userMenu.getMenuLevel()))
                .map(UserPrincipal::createMenu).collect(Collectors.toList());

        List<GrantedAuthority> authorities = rolePermissions.stream()
                .filter(rolePermission -> StrUtil.isNotBlank(rolePermission.getPermissionCode()))
                .map(rolePermission -> new SimpleGrantedAuthority(
                        rolePermission.getPageCode() + ":" + rolePermission.getPermissionCode()
                )).collect(Collectors.toList());

        return new UserPrincipal(systemuser.getUserId(),
                systemuser.getUsername(), systemuser.getPassword(),
                systemuser.getPhone(), systemuser.getNickname(), systemuser.getAvatar(), systemuser.getDescription(),
                systemuser.getStatus(),
                systemuser.getCreateTime(),
                systemuser.getUpdateTime(), systemuser.getLastLoginTime(), systemuser.getRoleName(),
                menus, authorities);
    }

    /**
     * Create MenuRO
     *
     * @param roleMenu MenuPO
     * @return MenuRO
     */
    private static Menu createMenu(SecurityGetRoleMenuListByRoleIdRO roleMenu) {
//        Menu menu = new Menu();
//        BeanUtil.copyProperties(roleMenu, menu);
//        if (Objects.equals(roleMenu.getMenuLevel(), MenuLevel.CHILD_MENU.getType())) {
//            menu.setUrl(roleMenu.getPage().getUrl());
//        }
//
//        if (!roleMenu.getChildren().isEmpty()) { //空指针异常
//            List<Menu> menus = new ArrayList<>();
//            roleMenu.getChildren().forEach(childNode -> {
//                Menu child = createMenu(childNode);
//                menus.add(child);
//            });
//            menu.setChildren(menus);
//        }
        return null;
    }

    @Data
    private static class Menu {
        private Long id;
        private String url;
        private String icon;
        private String menuCode;
        private String menuName;
        private String menuLevel;
        private Integer sortNo;
        private List<Menu> children = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.status, UserStatus.ENABLED.getStatus());
    }
}
