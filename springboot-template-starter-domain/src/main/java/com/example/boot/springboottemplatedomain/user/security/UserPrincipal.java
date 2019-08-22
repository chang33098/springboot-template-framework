package com.example.boot.springboottemplatedomain.user.security;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import com.example.boot.springboottemplatedomain.role.constants.MenuLevel;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.user.constants.UserStatus;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
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
 * <p>Custom user details.</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 20:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = -53353171692896501L;

    private Long id;
    private String username;
    private String password;
    private String phone;
    private String nickname;
    private String avatar;
    private String description;
    private Integer status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp lastLoginTime;

    private String roleName;
    private List<Menu> menus;
    private Collection<? extends GrantedAuthority> authorities;

    @Data
    private static class Menu {
        private Long id;
        private String url;
        private String icon;
        private String menuName;
        private Integer menuLevel;
        private Integer sortNo;
        private List<Menu> children = new ArrayList<>();
    }

    /**
     * Create user principal
     *
     * @param user        user po
     * @param roleMenus   user platform menus
     * @param permissions permission po list
     * @return user principal
     */
    public static UserPrincipal create(SystemUser user, List<RoleMenuRef> roleMenus, List<SystemPermission> permissions) {
        List<Menu> menus = roleMenus.stream().filter(userMenu -> Objects.equals(MenuLevel.PARENT_MENU.getType(), userMenu.getMenuLevel()))
                .map(UserPrincipal::createMenu).collect(Collectors.toList());

        List<GrantedAuthority> authorities = permissions.stream()
                .filter(permission -> StrUtil.isNotBlank(permission.getCode()))
                .map(permission -> new SimpleGrantedAuthority(permission.getCode()))
                .collect(Collectors.toList());

        return new UserPrincipal(user.getId(),
                user.getUsername(), user.getPassword(),
                user.getPhone(), user.getNickname(), user.getAvatar(), user.getDescription(),
                user.getStatus(),
                user.getCreateTime(),
                user.getUpdateTime(), user.getLastLoginTime(), user.getRole().getName(),
                menus, authorities);
    }

    /**
     * create MenuRO
     *
     * @param roleMenu MenuPO
     * @return MenuRO
     */
    private static Menu createMenu(RoleMenuRef roleMenu) {
        Menu menu = new Menu();
        BeanUtil.copyProperties(roleMenu, menu);

        menu.setUrl(roleMenu.getPage().getUrl());

        if (!roleMenu.getChildren().isEmpty()) {
            List<Menu> menus = new ArrayList<>();
            roleMenu.getChildren().forEach(childNode -> {
                Menu child = createMenu(childNode);
                menus.add(child);
            });
            menu.setChildren(menus);
        }

        return menu;
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
