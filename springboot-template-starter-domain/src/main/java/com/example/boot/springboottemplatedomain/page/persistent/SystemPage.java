package com.example.boot.springboottemplatedomain.page.persistent;

import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "page_id", columnDefinition = "bigint comment '页面ID'")
    private List<SystemPermission> permissions = new ArrayList<>();
}
