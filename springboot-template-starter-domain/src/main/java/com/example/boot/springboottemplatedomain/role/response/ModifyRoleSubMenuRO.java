package com.example.boot.springboottemplatedomain.role.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuPermissionRef;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by EDZ on 2019/8/22.
 */
@Data
public class ModifyRoleSubMenuRO {

    private Long id;
    private String icon;
    private String menuName;
    private Integer sortNo;
    private List<PagePermission> pagePermissions = new ArrayList<>();

    @Data
    public static class PagePermission {
        private Long permissionId;
        private String permissionName;
        private Boolean checked = false;
    }

    public static ModifyRoleSubMenuRO create(RoleMenuRef menuRef, List<PagePermissionRef> pagePermissions, List<RoleMenuPermissionRef> menuPermissions) {
        ModifyRoleSubMenuRO menuRO = new ModifyRoleSubMenuRO();
        BeanUtil.copyProperties(menuRef, menuRO);

        List<Long> menuPermissionId = menuPermissions.stream().map(menuPermission -> menuPermission.getPermission().getId()).collect(Collectors.toList());

        List<PagePermission> pagePermissionsROS = pagePermissions.stream().map(pagePermission -> {
            PagePermission pagePermissionRO = new PagePermission();
            pagePermissionRO.setPermissionId(pagePermission.getId());
            pagePermissionRO.setPermissionName(pagePermission.getPermission().getName());
            if (menuPermissionId.contains(pagePermission.getId())) pagePermissionRO.setChecked(true);

            return pagePermissionRO;
        }).collect(Collectors.toList());

        menuRO.setPagePermissions(pagePermissionsROS);

        return menuRO;
    }
}
