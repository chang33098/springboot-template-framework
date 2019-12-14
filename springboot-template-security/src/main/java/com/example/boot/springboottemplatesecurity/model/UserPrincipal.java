package com.example.boot.springboottemplatesecurity.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetRoleMenuListByRoleIdVO;
import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetRoleMenuPermissionListByMenuIdsVO;
import com.example.boot.springboottemplatebase.domain.systemuser.value.SecurityGetUserByUsernameVO;
import com.example.boot.springboottemplatebase.enums.MenuLevel;
import com.example.boot.springboottemplatebase.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
     * @param systemuser      systemuser po
     * @param roleMenus       systemuser platform menus
     * @param rolePermissions systemrole systempermission po list
     * @return systemuser principal
     */
    public static UserPrincipal create(SecurityGetUserByUsernameVO systemuser, List<SecurityGetRoleMenuListByRoleIdVO> roleMenus, List<SecurityGetRoleMenuPermissionListByMenuIdsVO> rolePermissions) {
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
    private static Menu createMenu(SecurityGetRoleMenuListByRoleIdVO roleMenu) {
        Menu menu = new Menu();
        BeanUtil.copyProperties(roleMenu, menu, "menuUrl");
        if (Objects.equals(roleMenu.getMenuLevel(), MenuLevel.CHILD_MENU.getType())) {
            menu.setMenuUrl(roleMenu.getMenuUrl());
        }

        if (!roleMenu.getChildren().isEmpty()) { //空指针异常
            List<Menu> menus = new ArrayList<>();
            roleMenu.getChildren().forEach(childNode -> {
                Menu child = createMenu(childNode);
                menus.add(child);
            });
            menu.setChildren(menus);
        }

        return menu;
    }

    @Data
    private static class Menu {
        private Long menuId;
        private String menuIcon;
        private String menuCode;
        private String menuName;
        private String menuUrl;
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
