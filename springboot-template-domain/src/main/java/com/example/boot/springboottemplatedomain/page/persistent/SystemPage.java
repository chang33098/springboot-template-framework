package com.example.boot.springboottemplatedomain.page.persistent;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 系统菜单模块
 *
 * @author ANdady
 * @date 2019/7/22 21:23
 */
@Data
@Entity
@Table(name = "system_page")
public class SystemPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) comment '模块代码(由英文和下划线组成)'")
    private String code;

    @Column(columnDefinition = "varchar(50) comment '页面名称'")
    private String name;

    @Column(columnDefinition = "varchar(100) comment '页面访问链接'")
    private String url;

    @Column(columnDefinition = "varchar(255) comment '页面作用描述'")
    private String description;

    @Column(columnDefinition = "datetime comment '创建时间'")
    private Timestamp createTime;

    @Column(columnDefinition = "datetime comment '修改时间'")
    private Timestamp updateTime;
}
