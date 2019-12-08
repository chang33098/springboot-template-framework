package com.example.boot.springboottemplatebase.base.persistent;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author chang_
 * @description 持久层PO公共父类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    @Column(columnDefinition = "create_by bigint default null comment '创建人ID'")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @Column(columnDefinition = "create_time datetime default null comment '创建时间(yyyy-MM-dd HH:mm:ss)'")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Timestamp createTime;

    /**
     * 修改人
     */
    @Column(columnDefinition = "update_by bigint default null comment '更新人ID'")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private Long updateBy;

    /**
     * 修改时间
     */
    @Column(columnDefinition = "update_time datetime default null comment '更新时间(yyyy-MM-dd HH:mm:ss)' ")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Timestamp updateTime;

    /**
     * 删除标记(0:未删除, 1:已删除)
     */
    @Column(columnDefinition = "deleted varchar(1) default '0' comment '删除标记(0:未删除,1:已删除)'")
    @TableField("deleted")
    @TableLogic
    private String deleted;
}
