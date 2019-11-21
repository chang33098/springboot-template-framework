package com.example.boot.springboottemplatedomain.role.persistent;

import com.baomidou.mybatisplus.annotation.*;

import java.sql.Timestamp;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * write this class description...
 *
 * @author chang_
 * @since 2019-11-17
 */
@Data
@Accessors(chain = true)
@TableName("system_role_menu_ref")
public class SystemRoleMenuRef implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单图标(使用layui的图标class)
     */
    @TableField("icon")
    private String icon;

    /**
     * 菜单级别(1:父菜单, 2:子菜单)
     */
    @TableField("menu_level")
    private Integer menuLevel;

    /**
     * 菜单代码(由菜单名称的拼音组成)
     */
    @TableField("menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 排序编号(默认为0)
     */
    @TableField("sort_no")
    private Integer sortNo;

    /**
     * 系统页面ID
     */
    @TableField("page_id")
    private Long pageId;

    /**
     * 系统角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 是否展开菜单 0:不展开, 1:展开
     */
    @TableField("opened")
    private Integer opened;

    /**
     * 创建人
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Timestamp createTime;

    /**
     * 修改人
     */
    @TableField("update_by")
    private Long updateBy;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Timestamp updateTime;

    /**
     * 删除标记(0:未删除,1:已删除)
     */
    @TableField("deleted")
    @TableLogic
    private String deleted;
}
