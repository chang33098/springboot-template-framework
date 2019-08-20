package com.example.boot.springboottemplatedomain.page.response;

import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/21 1:18
 */
@Data
public class PagePermissionRO {

    private Long id;
    private String permissionName;
    private String permissionCode;

    public static List<PagePermissionRO> createPagePermissionROS(List<PagePermissionRef> permissionRefs) {
        List<PagePermissionRO> permissionROS = new ArrayList<>();

        permissionRefs.forEach(permissionRef -> {
            PagePermissionRO permissionRO = new PagePermissionRO();
            permissionRO.setId(permissionRef.getId());
            permissionRO.setPermissionCode(permissionRef.getPermission().getCode());
            permissionRO.setPermissionName(permissionRef.getPermission().getName());

            permissionROS.add(permissionRO);
        });

        return permissionROS;
    }
}
