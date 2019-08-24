package com.example.boot.springboottemplatedomain.role.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EDZ on 2019/8/22.
 */
@Data
public class GetRoleMenuRO {

    private Long id;
    private String icon;
    private String menuName;
    private Integer sortNo;
    private List<PagePermission> pagePermissions = new ArrayList<>();

    @Data
    public static class PagePermission {
        private Long permissionId;
        private String permissionName;
        private Boolean checked;
    }
}
