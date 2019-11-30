package com.example.boot.springboottemplatebase.domain.systemrole.value;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * service、mapper[securityGetRoleMenuListRoleByRoleId]所返回的查询对象
 * tips: 以'QO'为后缀的对象只用于查询所使用的
 *
 * @author Chang
 * @date 2019/11/24 11:37
 */
@Data
@ToString
public class SecurityGetRoleMenuListByRoleIdVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色菜单ID
     */
    @TableField(value = "menu_id")
    private Long menuId;

    /**
     * 菜单URL
     */
    @TableField(value = "menu_url")
    private String menuUrl;

    /**
     * 菜单图标
     */
    @TableField(value = "menu_icon")
    private String menuIcon;

    /**
     * 菜单代码
     */
    @TableField(value = "menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 菜单等级
     */
    @TableField(value = "menu_level")
    private String menuLevel;

    /**
     * 排序编号
     */
    @TableField(value = "sort_no")
    private Integer sortNo;

    /**
     * 子菜单项目
     */
    private List<SecurityGetRoleMenuListByRoleIdVO> children;
}
