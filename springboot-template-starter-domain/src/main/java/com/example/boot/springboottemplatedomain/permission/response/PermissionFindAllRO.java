package com.example.boot.springboottemplatedomain.permission.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/11 13:16
 */
@Data
public class PermissionFindAllRO {

    private Long id;
    private String name;
    private String code;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;

    /**
     * Create PermissionFindAllRO List. Transfer PO to the RO.
     *
     * @param permissions PermissionPO List
     * @return PermissionFindAllRO
     */
    public static List<PermissionFindAllRO> createPermissionFindAllROS(List<SystemPermission> permissions) {
        List<PermissionFindAllRO> permissionROS = new ArrayList<>(permissions.size());

        permissions.forEach(permission -> {
            PermissionFindAllRO permissionRO = new PermissionFindAllRO();
            BeanUtil.copyProperties(permission, permissionRO);

            permissionROS.add(permissionRO);
        });

        return permissionROS;
    }
}
