package com.example.boot.springboottemplatedomain.page.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/3 15:20
 */
@Data
public class GetPageRO {

    private Long id;
    private String code;
    private String name;
    private String url;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;

    private List<PagePermission> pagePermissions = new ArrayList<>();

    @Data
    public static class PagePermission {
        private Long permissionId;
        private String permissionName;
        private String permissionCode;
        private String interceptUrls;
    }

    public static GetPageRO create(SystemPage page, List<PagePermissionRef> permissionRefs) {
        GetPageRO pageRO = new GetPageRO();
        BeanUtil.copyProperties(page, pageRO);

        List<PagePermission> permissionROS = permissionRefs.stream().map(permissionRef -> {
            PagePermission pagePermission = new PagePermission();
            pagePermission.setPermissionId(permissionRef.getPermission().getId());
            pagePermission.setPermissionName(permissionRef.getPermission().getName());
            pagePermission.setPermissionCode(permissionRef.getPermission().getCode());
            pagePermission.setInterceptUrls(permissionRef.getInterceptUrls());

            return pagePermission;
        }).collect(Collectors.toList());
        pageRO.setPagePermissions(permissionROS);

        return pageRO;
    }
}
