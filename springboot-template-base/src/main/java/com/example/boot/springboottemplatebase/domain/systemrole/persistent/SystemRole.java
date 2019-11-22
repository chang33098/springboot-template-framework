package com.example.boot.springboottemplatebase.domain.systemrole.persistent;

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
@TableName("system_role")
public class SystemRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色描述
     */
    @TableField("description")
    private String description;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

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
     * 更新时间
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
