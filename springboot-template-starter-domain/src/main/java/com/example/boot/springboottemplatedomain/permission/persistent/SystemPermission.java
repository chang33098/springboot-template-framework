package com.example.boot.springboottemplatedomain.permission.persistent;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * description write this class description...
 *
 * @author ANdady
 * @date 2019/7/21 20:32
 */
@Data
@Entity
@Table(name = "system_permission")
public class SystemPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) comment '权限名称'")
    private String name;

    @Column(columnDefinition = "varchar(50) comment '权限代码(由英文和下划线组成)'")
    private String code;

    @Column(columnDefinition = "varchar(255) comment '作用描述'")
    private String description;

    @Column(columnDefinition = "datetime comment '创建时间'")
    private Timestamp createTime;

    @Column(columnDefinition = "datetime comment '更新时间'")
    private Timestamp updateTime;

    @OneToMany
    @JoinColumn(name = "permission_id", columnDefinition = "int comment '系统权限ID'")
    private List<SystemPermissionUrl> permissionUrls;
}
