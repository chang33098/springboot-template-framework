package com.example.boot.springboottemplatedomain.role.persistent;

import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 角色-菜单关联表
 *
 * @author Chang
 * @date 2019/7/22 22:51
 */
@Data
@Entity
@Table(name = "system_role_menu_ref")
public class RoleMenuRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) comment '菜单图标(使用layui的图标class)'")
    private String icon;

    @Column(columnDefinition = "tinyint comment '菜单级别(1:父菜单, 2:子菜单)'")
    private int menuLevel;

    @Column(columnDefinition = "tinyint comment '排序编号(默认为0)'")
    private int sortNo;

    @ManyToOne
    @JoinColumn(name = "role_id", columnDefinition = "bigint comment '系统角色ID'")
    private SystemRole role;

    @ManyToOne
    @JoinColumn(name = "page_id", columnDefinition = "bigint comment '系统页面ID'")
    private SystemPage page;

    @OneToMany
    @JoinColumn(name = "parent_id", columnDefinition = "bigint comment '父菜单ID'")
    private List<RoleMenuRef> childNodes;

    @OneToMany
    @JoinColumn(name = "menu_id", columnDefinition = "bigint comment '菜单ID'")
    private List<SystemPermission> permissions;
}
