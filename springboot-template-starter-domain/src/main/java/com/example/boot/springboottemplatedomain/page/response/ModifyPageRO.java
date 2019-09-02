package com.example.boot.springboottemplatedomain.page.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
}
