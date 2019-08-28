package com.example.boot.springboottemplatedomain.user.persistent;

import lombok.Data;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * description write this class description...
 *
 * @author ANdady
 * @date 2019/7/21 19:41
 */
@Data
@Entity
@Table(name = "system_user", indexes = {
        @Index(name = "username_index", columnList = "username", unique = true)
})
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(100) comment '登陆账号'")
    private String username;

    @Column(columnDefinition = "varchar(100) comment '登陆密码'")
    private String password;

    @Column(columnDefinition = "varchar(50) comment '手机号码'")
    private String phone;

    @Column(columnDefinition = "varchar(100) comment '昵称'")
    private String nickname;

    @Column(columnDefinition = "varchar(100) comment '头像'")
    private String avatar;

    @Column(columnDefinition = "varchar(500) comment '用户描述'")
    private String description;

    @Column(columnDefinition = "tinyint comment '用户状态 1:正常, 2:已禁用'")
    private Integer status;

    @Column(columnDefinition = "tinyint comment '删除标记 0:未删除, 1:已删除'")
    private Boolean deleted;

    @Column(columnDefinition = "datetime comment '创建时间'")
    private Timestamp createTime;

    @Column(columnDefinition = "datetime comment '修改时间'")
    private Timestamp updateTime;

    @Column(columnDefinition = "datetime comment '最后一次登录时间'")
    private Timestamp lastLoginTime;

    @ManyToOne
    @JoinColumn(name = "role_id", columnDefinition = "bigint comment '系统角色ID'")
    private SystemRole role;
}
