package com.example.boot.springboottemplatebase.domain.systemrole.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.boot.springboottemplatebase.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * <p>系统角色entity</p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Data
@Entity
@Table(name = "system_role")
@TableName(value = "system_role")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SystemRoleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @Column(columnDefinition = "varchar(50) default null comment '角色名称'")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(columnDefinition = "varchar(255) default null comment '角色描述'")
    private String description;
}
