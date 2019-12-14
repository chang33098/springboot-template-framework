package com.example.boot.springboottemplatebase.domain.systemrole.value;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * service、mapper[SecurityGetRoleMenuPermissionListByMenuIds]所返回的查询对象
 * tips: 以'QO'为后缀的对象只用于查询所使用的
 *
 * @author Chang
 * @date 2019/11/24 13:25
 */
@Data
public class SecurityGetRoleMenuPermissionListByMenuIdsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页面代码
     */
    @TableField(value = "page_code")
    private String pageCode;

    /**
     * 权限代码
     */
    @TableField(value = "permission_code")
    private String permissionCode;
}
