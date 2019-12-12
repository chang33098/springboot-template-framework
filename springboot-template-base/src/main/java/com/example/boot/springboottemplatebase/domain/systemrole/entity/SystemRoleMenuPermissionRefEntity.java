package com.example.boot.springboottemplatebase.domain.systemrole.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import com.example.boot.springboottemplatebase.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * <p>系统角色-菜单-权限entity</p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Data
@Entity
@Table(name = "system_role_menu_permission_ref")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SystemRoleMenuPermissionRefEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联的角色ID
     */
    @Column(columnDefinition = "bigint default null comment '关联的角色ID'")
    private Long roleId;

    /**
     * 角色页面ID
     */
    @Column(columnDefinition = "bigint default null comment '角色页面ID'")
    private Long roleMenuId;

    /**
     * 页面权限ID
     */
    @Column(columnDefinition = "bigint default null comment '页面权限ID'")
    private Long pagePermissionId;
}
