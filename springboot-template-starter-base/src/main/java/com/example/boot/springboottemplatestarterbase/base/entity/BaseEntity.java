package com.example.boot.springboottemplatestarterbase.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author chang_
 * @description 持久层PO公共父类
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
