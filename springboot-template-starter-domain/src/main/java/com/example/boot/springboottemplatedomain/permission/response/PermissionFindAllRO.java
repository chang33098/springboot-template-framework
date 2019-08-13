package com.example.boot.springboottemplatedomain.permission.response;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/13 23:00
 */
@Data
public class PermissionFindAllRO {

    private Long id;
    private String name;
    private String code;
    private String description;
    private Timestamp createTime;
    private Timestamp updateTime;

    /**
     * PermissionPO transfer to PermissionFindAllRO
     *
     * @param permissions PermissionPOS
     * @return PermissionFindAllROS
     */
    public static List<PermissionFindAllRO> createPermissionFindAllROS(List<SystemPermission> permissions) {
        List<PermissionFindAllRO> permissionROS = permissions.stream().map(permission -> {
            PermissionFindAllRO permissionRO = new PermissionFindAllRO();
            BeanUtil.copyProperties(permission, permissionRO);
            return permissionRO;
        }).collect(Collectors.toList());

        return permissionROS;
    }
}
