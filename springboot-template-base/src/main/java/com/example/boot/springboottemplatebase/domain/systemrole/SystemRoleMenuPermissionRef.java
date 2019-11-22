package com.example.boot.springboottemplatebase.domain.systemrole;

import com.baomidou.mybatisplus.annotation.*;

import java.sql.Timestamp;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * write this class description...
 *
 * @author chang_
 * @since 2019-11-17
 */
@Data
@Accessors(chain = true)
@TableName("system_role_menu_permission_ref")
public class SystemRoleMenuPermissionRef implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联的角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 角色页面ID
     */
    @TableField("role_menu_id")
    private Long roleMenuId;

    /**
     * 页面权限ID
     */
    @TableField("page_permission_id")
    private Long pagePermissionId;

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
