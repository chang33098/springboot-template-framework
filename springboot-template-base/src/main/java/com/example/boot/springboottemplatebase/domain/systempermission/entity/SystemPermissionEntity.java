package com.example.boot.springboottemplatebase.domain.systempermission.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.io.Serializable;

import com.example.boot.springboottemplatebase.base.persistent.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * <p>系统权限entity</p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Data
@Entity
@Table(name = "system_permission")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SystemPermissionEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限代码(由英文和下划线组成)
     */
    @Column(columnDefinition = "varchar(50) default null comment '权限代码'")
    private String permissionCode;

    /**
     * 权限名称
     */
    @Column(columnDefinition = "varchar(100) default null comment '权限名称'")
    private String permissionName;

    /**
     * 作用描述
     */
    @Column(columnDefinition = "varchar(255) default null comment '权限作用描述'")
    private String description;
}
