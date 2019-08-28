package com.example.boot.springboottemplatedomain.page.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/3 15:22
 */
@Data
public class ModifyPageRO {

    private Long id;
    private String code;
    private String name;
    private String url;
    private String description;
    private List<PagePermission> pagePermissions = new ArrayList<>();

    @Data
    public static class PagePermission {
        private Long permissionId;
        private String permissionName;
        private String interceptUrls;
    }

    public static ModifyPageRO create(SystemPage page, List<PagePermissionRef> permissionRefs) {
        ModifyPageRO pageRO = new ModifyPageRO();
        BeanUtil.copyProperties(page, pageRO);

        List<PagePermission> permissionROS = permissionRefs.stream().map(permissionRef -> {
            PagePermission pagePermission = new PagePermission();
            pagePermission.setPermissionId(permissionRef.getPermission().getId());
            pagePermission.setPermissionName(permissionRef.getPermission().getName());
            pagePermission.setInterceptUrls(permissionRef.getInterceptUrls());

            return pagePermission;
        }).collect(Collectors.toList());

        pageRO.setPagePermissions(permissionROS);

        return pageRO;
    }
}
