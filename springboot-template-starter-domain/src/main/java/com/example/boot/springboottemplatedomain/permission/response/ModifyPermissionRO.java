package com.example.boot.springboottemplatedomain.permission.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermissionUrl;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static ModifyPermissionRO createPermissionRO(SystemPermission permission, List<SystemPermissionUrl> permissionUrls) {
        ModifyPermissionRO permissionRO = new ModifyPermissionRO();
        BeanUtil.copyProperties(permission, permissionRO);

        List<PermissionUrl> urlROS = permissionUrls.stream().map(permissionUrl -> {
            PermissionUrl urlRO = new PermissionUrl();
            BeanUtil.copyProperties(permissionUrl, urlRO);

            return urlRO;
        }).collect(Collectors.toList());

        permissionRO.setPermissionUrls(urlROS);

        return permissionRO;
    }

    @Data
    public static class PermissionUrl {
        private Long matchUrl;
        private Integer sortNo;
    }
}
