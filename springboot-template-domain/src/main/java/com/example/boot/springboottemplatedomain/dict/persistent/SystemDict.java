package com.example.boot.springboottemplatedomain.dict.persistent;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chang_
 * @since 2019-11-13
 */
@Data
@Accessors(chain = true)
@TableName("system_dict")
public class SystemDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典描述
     */
    @TableField("description")
    private String description;

    /**
     * 字典类型名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典类型(由大写英文和下划线组成)
     */
    @TableField("dict_code")
    private String dictCode;

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
     * 删除标记(0:未删除, 1:已删除)
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}
