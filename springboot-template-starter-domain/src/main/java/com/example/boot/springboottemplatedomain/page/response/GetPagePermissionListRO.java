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
public class GetPagePermissionListRO {

    private Long id;
    private String permissionName;
    private String permissionCode;

    public static List<GetPagePermissionListRO> createPagePermissionROS(List<PagePermissionRef> permissionRefs) {
        List<GetPagePermissionListRO> permissionROS = new ArrayList<>();

        permissionRefs.forEach(permissionRef -> {
            GetPagePermissionListRO permissionRO = new GetPagePermissionListRO();
            permissionRO.setId(permissionRef.getId());
            permissionRO.setPermissionCode(permissionRef.getPermission().getCode());
            permissionRO.setPermissionName(permissionRef.getPermission().getName());

            permissionROS.add(permissionRO);
        });

        return permissionROS;
    }
}
