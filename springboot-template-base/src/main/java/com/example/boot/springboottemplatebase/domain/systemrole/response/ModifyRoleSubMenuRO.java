package com.example.boot.springboottemplatebase.domain.systemrole.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EDZ on 2019/8/22.
 */
@Data
public class ModifyRoleSubMenuRO {

    private Long id;
    private String icon;
    private String menuName;
    private Integer sortNo;
    private Long pageId;
    private String pageName;
    private String pageCode;
    private String pageUrl;
    private List<PagePermission> pagePermissions = new ArrayList<>();

    @Data
    public static class PagePermission {
        private Long permissionId;
        private String permissionName;
        private Boolean checked = false;
    }
}
