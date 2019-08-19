package com.example.boot.springboottemplatedomain.role.persistent;

import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import lombok.Data;

import javax.persistence.*;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/20 0:25
 */
@Data
@Entity
@Table(name = "system_role_menu_permission_ref")
public class RoleMenuPermissionRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "page_permission_id", columnDefinition = "bigint comment '页面权限ID'")
    private PagePermissionRef permission;

    @ManyToOne
    @JoinColumn(name = "role_menu_id", columnDefinition = "bigint comment '角色页面ID'")
    private RoleMenuRef menu;
}
