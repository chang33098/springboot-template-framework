package com.example.boot.model.mybatistest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author chang_
 * @since 2019-11-10
 */
@Data
@Accessors(chain = true)
@TableName("mybatis_test1")
public class MybatisTest1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * stringColumn
     */
    @TableField("stringColumn")
    private String stringColumn;

    /**
     * intColumn
     */
    @TableField("intColumn")
    private Integer intColumn;

    /**
     * decimalColumn
     */
    @TableField("doubleColumn")
    private BigDecimal doubleColumn;

    /**
     * timestampColumn
     */
    @TableField("timestampColumn")
    private Timestamp timestampColumn;

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
