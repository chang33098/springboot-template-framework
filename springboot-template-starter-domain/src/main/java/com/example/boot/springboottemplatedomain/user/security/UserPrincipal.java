package com.example.boot.springboottemplatedomain.user.security;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import com.example.boot.springboottemplatedomain.role.constants.MenuLevel;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatedomain.user.constants.UserStatus;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private List<UserMenuRO> menus;
    private Collection<? extends GrantedAuthority> authorities;

    @Data
    private static class UserMenuRO {
        private Long id;
        private String url;
        private String icon;
        private String menuName;
        private Integer menuLevel;
        private Integer sortNo;
        private List<UserMenuRO> leafNodes = new ArrayList<>();
    }

    /**
     * Create user principal
     *
     * @param user        user po
     * @param userMenus   user platform menus
     * @param permissions permission po list
     * @return user principal
     */
    public static UserPrincipal create(SystemUser user, List<RoleMenuRef> userMenus, List<SystemPermission> permissions) {
        List<UserMenuRO> menus = userMenus.stream().filter(userMenu -> MenuLevel.parentMenu(userMenu.getMenuLevel()))
                .map(UserPrincipal::createMenuRO).collect(Collectors.toList());

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
     * @param menuPO MenuPO
     * @return MenuRO
     */
    private static UserMenuRO createMenuRO(RoleMenuRef menuPO) {
        UserMenuRO menuRO = new UserMenuRO();
        BeanUtil.copyProperties(menuPO, menuRO);

        menuRO.setUrl(menuPO.getPage().getUrl());

        if (!menuPO.getChildNodes().isEmpty()) {
            List<UserMenuRO> menuROS = new ArrayList<>();
            menuPO.getChildNodes().forEach(childNode -> {
                UserMenuRO childNodeRO = createMenuRO(childNode);
                menuROS.add(childNodeRO);
            });

            menuRO.setLeafNodes(menuROS);
        }

        return menuRO;
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
