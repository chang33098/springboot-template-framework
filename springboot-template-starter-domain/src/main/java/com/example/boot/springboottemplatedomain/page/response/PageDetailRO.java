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
public class PageDetailRO {

    private Long id;
    private String code;
    private String name;
    private String url;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;

    private List<PageDetailPermissionRO> pagePermissions = new ArrayList<>();

    @Data
    public static class PageDetailPermissionRO {
        private Long permissionId;
        private String permissionName;
        private String permissionCode;
        private String interceptUrls;
    }

    public static PageDetailRO createPageDetailRO(SystemPage page, List<PagePermissionRef> permissionRefs) {
        PageDetailRO pageRO = new PageDetailRO();
        BeanUtil.copyProperties(page, pageRO);

        List<PageDetailPermissionRO> permissionROS = permissionRefs.stream().map(permissionRef -> {
            PageDetailPermissionRO pagePermissionRO = new PageDetailPermissionRO();
            pagePermissionRO.setPermissionId(permissionRef.getPermission().getId());
            pagePermissionRO.setPermissionName(permissionRef.getPermission().getName());
            pagePermissionRO.setPermissionCode(permissionRef.getPermission().getCode());
            pagePermissionRO.setInterceptUrls(permissionRef.getInterceptUrls());

            return pagePermissionRO;
        }).collect(Collectors.toList());
        pageRO.setPagePermissions(permissionROS);

        return pageRO;
    }
}
