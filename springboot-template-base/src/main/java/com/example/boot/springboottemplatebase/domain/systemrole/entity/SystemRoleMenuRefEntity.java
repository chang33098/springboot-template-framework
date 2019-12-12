package com.example.boot.springboottemplatebase.domain.systemrole.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.boot.springboottemplatebase.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>系统角色-菜单entity</p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Data
@Entity
@Table(name = "system_role_menu_ref")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SystemRoleMenuRefEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单图标(使用layui的图标class)
     */
    @Column(columnDefinition = "varchar(50) default null comment '菜单图标'")
    private String menuIcon;

    /**
     * 菜单级别(1:父菜单, 2:子菜单)
     */
    @Column(columnDefinition = "varchar(5) default null comment '菜单级别(1:父菜单,2:子菜单)'")
    private String menuLevel;

    /**
     * 菜单代码(由菜单名称的拼音组成)
     */
    @Column(columnDefinition = "varchar(50) default null comment '菜单代码'")
    private String menuCode;

    /**
     * 菜单名称
     */
    @Column(columnDefinition = "varchar(50) default null comment '菜单名称'")
    private String menuName;

    /**
     * 排序编号(默认为0)
     */
    @Column(columnDefinition = "int default 0 comment '排序编号'")
    private Integer sortNo;

    /**
     * 系统页面ID
     */
    @Column(columnDefinition = "bigint default null comment '系统页面ID(system_page.id)'")
    private Long pageId;

    /**
     * 系统角色ID
     */
    @Column(columnDefinition = "bigint default null comment '系统角色ID(system_role.id)'")
    private Long roleId;

    /**
     * 父菜单ID
     */
    @Column(columnDefinition = "bigint default null comment '父节点ID(system_role_menu.id)'")
    private Long parentId;

    /**
     * 是否展开菜单 0:不展开, 1:展开
     */
    @Column(columnDefinition = "varchar(5) default null comment '是否展开菜单(0:不展开,1:展开)'")
    private String opened;
}
