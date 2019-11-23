package com.example.boot.springboottemplatebase.domain.systempage.persistent;

import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chang_
 * @since 2019-11-16
 */
@Data
@Accessors(chain = true)
@TableName("system_page_permission_ref")
public class SystemPagePermissionRef implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限所拦截的URL
     */
    @TableField("intercept_urls")
    private String interceptUrls;

    /**
     * 系统页面ID
     */
    @TableField("page_id")
    private Long pageId;

    /**
     * 系统权限ID
     */
    @TableField("permission_id")
    private Long permissionId;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Timestamp createTime;

    /**
     * 修改人
     */
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private Long updateBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Timestamp updateTime;

    /**
     * 删除标记(0:未删除,1:已删除)
     */
    @TableField("deleted")
    @TableLogic
    private String deleted;
}
