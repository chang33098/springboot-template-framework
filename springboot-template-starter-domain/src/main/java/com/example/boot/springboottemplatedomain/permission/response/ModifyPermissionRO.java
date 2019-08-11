package com.example.boot.springboottemplatedomain.permission.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/12 0:43
 */
@Data
public class ModifyPermissionRO {

    private Long id;
    private String name;
    private String code;
    private List<PermissionUrl> permissionUrls = new ArrayList<>();

    @Data
    public static class PermissionUrl {
        private Long matchUrl;
        private Integer sortNo;
    }
}
