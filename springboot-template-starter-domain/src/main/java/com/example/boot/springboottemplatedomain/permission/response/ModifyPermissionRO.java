package com.example.boot.springboottemplatedomain.permission.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import lombok.Data;

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
    private String description;

    public static ModifyPermissionRO createModifyPermissionRO(SystemPermission permission) {
        ModifyPermissionRO permissionRO = new ModifyPermissionRO();
        BeanUtil.copyProperties(permission, permissionRO);

        return permissionRO;
    }
}
