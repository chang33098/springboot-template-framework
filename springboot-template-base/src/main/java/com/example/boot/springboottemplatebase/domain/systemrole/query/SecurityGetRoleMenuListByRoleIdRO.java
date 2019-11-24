package com.example.boot.springboottemplatebase.domain.systemrole.query;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * service、mapper[securityGetRoleMenuListRoleByRoleId]所返回的查询对象
 * tips: 以'QO'为后缀的对象只用于查询所使用的
 *
 * @author Chang
 * @date 2019/11/24 11:37
 */
@Data
public class SecurityGetRoleMenuListByRoleIdRO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色菜单ID
     */
    @TableField(value = "menu_id")
    private Long menuId;

    /**
     * 菜单等级
     */
    @TableField(value = "menu_level")
    private String menuLevel;
}
