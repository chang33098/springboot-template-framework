package com.example.boot.springboottemplatedomain.role.persistent;

import com.example.boot.springboottemplatedomain.page.persistent.SystemMenu;
import lombok.Data;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "role_id", columnDefinition = "bigint comment '系统角色ID'")
    private SystemRole role;

    @ManyToOne
    @JoinColumn(name = "menu_id", columnDefinition = "bigint comment '系统菜单ID'")
    private SystemMenu menu;
}
