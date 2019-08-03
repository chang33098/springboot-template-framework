package com.example.boot.springboottemplatedomain.permission.persistent;

import lombok.Data;

import javax.persistence.*;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/22 22:39
 */
@Data
@Entity
@Table(name = "system_permission_url")
public class SystemPermissionUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(100) comment '权限可访问的URL'")
    private String url;

    @Column(columnDefinition = "varchar(50) comment '排序编号'")
    private int sortNo;

    @ManyToOne
    @JoinColumn(name = "permission_id", columnDefinition = "bigint comment '系统权限ID'")
    private SystemPermission permission;
}
