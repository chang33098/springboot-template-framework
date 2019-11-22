package com.example.boot.springboottemplatebase.domain.systempage.persistent;

import com.baomidou.mybatisplus.annotation.*;

import java.sql.Timestamp;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Data
@Accessors(chain = true)
@TableName("system_page")
public class SystemPage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模块代码(由英文和下划线组成)
     */
    @TableField("code")
    private String code;

    /**
     * 页面作用描述
     */
    @TableField("description")
    private String description;

    /**
     * 页面名称
     */
    @TableField("name")
    private String name;

    /**
     * 页面访问链接
     */
    @TableField("url")
    private String url;

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
