package com.example.boot.springboottemplatedomain.page.persistent;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * 系统菜单模块
 *
 * @author ANdady
 * @date 2019/7/22 21:23
 */
@Data
@Entity
@Table(name = "system_menu")
public class SystemMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) comment '菜单图标ID'")
    private String icon;

    @Column(columnDefinition = "varchar(50) comment '菜单名称'")
    private String name;

    @Column(columnDefinition = "varchar(100) comment '菜单访问链接(若为父级菜单, 则无访问链接)'")
    private String url;

    @Column(columnDefinition = "varchar(255) comment '菜单作用描述'")
    private String description;

    @Column(columnDefinition = "tinyint comment '是否为父节点(1:父菜单, 2:子菜单)'")
    private int isRoot;

    @Column(columnDefinition = "tinyint comment '排序编号(默认为0)'")
    private int sortNo;

    @Column(columnDefinition = "datetime comment '创建时间'")
    private Timestamp createTime;

    @Column(columnDefinition = "datetime comment '修改时间'")
    private Timestamp updateTime;

    @OneToMany
    @JoinColumn(name = "parent_id", columnDefinition = "int comment '父菜单ID'")
    private List<SystemMenu> menus;
}
