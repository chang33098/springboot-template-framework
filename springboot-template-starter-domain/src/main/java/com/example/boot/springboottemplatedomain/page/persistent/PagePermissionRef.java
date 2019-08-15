package com.example.boot.springboottemplatedomain.page.persistent;

import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import lombok.Data;

import javax.persistence.*;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/15 22:44
 */
@Data
@Entity
@Table(name = "system_page_permission_ref")
public class PagePermissionRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(500) comment '权限所拦截的URL'")
    private String interceptUrls;

    @OneToOne
    @JoinColumn(name = "page_id", columnDefinition = "bigint comment '系统页面ID'")
    private SystemPage page;

    @OneToOne
    @JoinColumn(name = "permission_id", columnDefinition = "bigint comment '系统权限ID'")
    private SystemPermission permission;
}
